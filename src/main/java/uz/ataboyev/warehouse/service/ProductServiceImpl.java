package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.ProductRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BaseService baseService;

    @Override
    public ApiResult<?> add(ProductReqDto productReqDto) {

        //MAXSULOT FIRMASI TEKSHIRILYABDI
        if (!baseService.checkProductCompanyById(productReqDto.getProductCompanyId()))
            throw RestException.restThrow("Mahsulotning kompaniyasi topilmadi", HttpStatus.NOT_FOUND);

        if (productRepository.existsByName(productReqDto.getName()))
            throw RestException.restThrow("Bu mahsulot bizda mavjud", HttpStatus.CONFLICT);

        Product product = Product.make(productReqDto);

        savedProduct(product);

        ProductResDto productResDto = ProductResDto.makeDTO(product);

        return ApiResult.successResponse(productResDto,"product muvafaqiyatli saqlandi");
    }

    @Override
    public ApiResult<?> getOne(Long productId) {

        Product product = baseService.getProductByIdOrElseThrow(productId);

        ProductResDto productResDto = ProductResDto.makeDTO(product);

        return ApiResult.successResponse(productResDto);
    }

    @Override
    public List<ProductResDto> getAllProductsByPCId(Long pCId) {

        List<Product> productList = productRepository.findAllByProductCompanyId(pCId);

        return productList.stream().map(ProductResDto::makeDTO).collect(Collectors.toList());
    }

    @Override
    public List<OptionResDto> getProductsForOptionByPCId(Long pCId) {
        List<OptionResIn> productList = productRepository.findDistinctByProductCompanyId(pCId);
        return productList.stream().map(OptionResDto::make).collect(Collectors.toList());
    }


    @Override
    public List<ProductResDtoByWhId> getAllProductByWarehouseId(Long warehouseId) {
        List<ProductResDtoByWhIdImpl> products =
                productRepository.getProductByWarehouseId(warehouseId);

        return products.stream().map(ProductResDtoByWhId::makeDTO).collect(Collectors.toList());
    }


    @Override
    public List<ProductResDtoByWhId> littleProductsByWarehouseId(Long whId) {
        List<ProductResDtoByWhIdImpl> products =
                productRepository.getLittleProductByWarehouseId(whId);
        return products.stream().map(ProductResDtoByWhId::makeDTO).collect(Collectors.toList());
    }

    //PRODUCTNI NOMINI BERIB YUBORISH ORQALI SHU NOMLI PRODUCTLARNING CODELARINI OLIB KELADI
    @Override
    public List<GetCodesForProductDto> getCodesForProduct(String productName) {
        List<GetCodesForProduct> productCodes = productRepository.getCodesForProduct(productName);
        return productCodes.stream().map(GetCodesForProductDto::make).collect(Collectors.toList());
    }

    @Override
    public ApiResult<?> edit(Long productId, ProductReqDto productReqDto) {
        Product oldProduct = baseService.getProductByIdOrElseThrow(productId);
        Product editedProduct = editProduct(oldProduct,productReqDto);
        productRepository.save(editedProduct);
        return ApiResult.successResponse("Mahsulot muvafaqiyatli o'zgartirildi");
    }

    private Product editProduct(Product oldProduct, ProductReqDto productReqDto) {

        boolean check = productRepository.existsByNameAndIdNot(productReqDto.getName(), oldProduct.getId());

        if (check)throw RestException.restThrow("Bu nomli va kodli Mahsulot bazada oldindan mavjud");

        oldProduct.setMinCount(productReqDto.getMinCount());
        oldProduct.setName(productReqDto.getName());

        return oldProduct;
    }

    @Override
    public ApiResult<?> delete(Long productId) {
        //todo productni o'rdera qarab ishlimiz so'ng inshaalloh
        return null;
    }

    @Override
    public void checkingProductByIdListOrElseThrow(List<Long> productIdList) {

        Integer productsCount = productRepository.countByIdIn(productIdList);

        if (!Objects.equals(productsCount, productIdList.size()))
            throw RestException.restThrow("Mavjud bolmagan product id berildi =>" + productIdList);

    }

    private void savedProduct(Product product) {
        try{
            productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            throw RestException.restThrow("Product saqlashda muommo bo'ldi",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

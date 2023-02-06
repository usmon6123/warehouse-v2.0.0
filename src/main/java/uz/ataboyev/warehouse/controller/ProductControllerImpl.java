package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ApiResult<?> add(ProductReqDto productReqDto) {
        return productService.add(productReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long productId) {
        return productService.getOne(productId);
    }

    @Override
    public List<ProductResDto> getAllProductByPCId(Long pCId) {
        return productService.getAllProductsByPCId(pCId);
    }

    @Override
    public List<ProductResDtoByWhId> getAllProductByWarehouseId(Long warehouseId) {
        return productService.getAllProductByWarehouseId(warehouseId);
    }

    @Override
    public List<OptionResDto> getProductsForOption(Long whId) {
        return productService.getProductsForOptionByWhId(whId);
    }

    @Override
    public List<ProductResDtoByWhId> littleProductsByWarehouseId(Long whId) {
        return productService.littleProductsByWarehouseId(whId);
    }


    @Override
    public ApiResult<?> edit(Long productId, ProductReqDto productReqDto) {
        return productService.edit(productId, productReqDto);
    }

    @Override
    public ApiResult<?> delete(Long productId) {
        return productService.delete(productId);
    }
}

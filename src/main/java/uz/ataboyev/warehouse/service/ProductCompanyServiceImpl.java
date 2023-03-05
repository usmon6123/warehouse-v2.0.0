package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.ProductCompany;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.ProductCompanyRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCompanyServiceImpl implements ProductCompanyService {


    private final BaseService baseService;
    private final ProductCompanyRepository productCompanyRepository;


    @Override
    public ApiResult<?> add(ProductCompanyReqDto productCompanyReqDto) {
        try {
//            //NOMINI UNIQLIKKA TEKSHIRADI
//            if (productCompanyRepository.existsByName(productCompanyReqDto.getName()) && baseService.existsWarehouse(productCompanyReqDto.getWhId()))
//                throw RestException.restThrow("Maxsulot firmasi qo'shishda xatolik bo'ldi", HttpStatus.CONFLICT);
            if(!baseService.existsWarehouse(productCompanyReqDto.getWhId()))return ApiResult.errorResponse("Maxsulot firmasi qo'shishda xatolik bo'ldi");

            ProductCompany productCompany = productCompanyRepository.save(Objects.requireNonNull(ProductCompany.make(productCompanyReqDto)));
            return ApiResult.successResponse("success added product company id: " + productCompany.getId() + " name: " + productCompany.getName());
        } catch (Exception e) {
            return ApiResult.errorResponse("Maxsulot firmasi qo'shishda xatolik bo'ldi");
        }
    }

    @Override
    public ApiResult<?> getOne(Long prodCompId) {

        ProductCompany productCompany = baseService.getProdCompanyById(prodCompId);

        return ApiResult.successResponse(ProductCompanyResDto.make(productCompany));
    }

    @Override
    public List<OptionResDto> getAll(Long whId) {
        List<OptionInIdName> option = productCompanyRepository.getAllForOptionByWhId(whId);
        return option.stream().map(OptionResDto::make).collect(Collectors.toList());

    }

    @Override
    public List<OptionResDto> getAllForOption(Long whId) {
        List<OptionResIn> option = productCompanyRepository.findAllForOptionByWhId(whId);
        return option.stream().map(OptionResDto::make).collect(Collectors.toList());
    }

    @Override
    public ApiResult<?> edit(Long pCId, ProductCompanyReqDto productCompanyReqDto) {

        if (!baseService.checkProductCompanyById(pCId))
            return ApiResult.errorResponse("O'zgartirmoqchi bo'lgan mmaxsulot firmasi mavjudmas");
        if (baseService.checkProductCompanyWhIdAndName(productCompanyReqDto,pCId))
            return ApiResult.errorResponse("Sizning omborxonangizda bu nomli mmaxsulot firmasi mavjud");
        ProductCompany prodCompany = baseService.getProdCompanyById(pCId);
        ProductCompany.edit(prodCompany, productCompanyReqDto);
        productCompanyRepository.save(prodCompany);
        return ApiResult.successResponse("Muvafaqiyatli o'zgartiroldi");
    }


    @Override
    public ApiResult<?> delete(Long compId) {

        if (!productCompanyRepository.existsById(compId))
            throw RestException.restThrow("Firma mavjudmas emas", HttpStatus.NOT_FOUND);
        if (baseService.existsWarehouseByCompId(compId))
            throw RestException.restThrow("Firmaning Maxsulotlari bor o'chira olmaysiz", HttpStatus.CONFLICT);
        productCompanyRepository.deleteById(compId);

        return ApiResult.successResponse("deleted company");
    }
}

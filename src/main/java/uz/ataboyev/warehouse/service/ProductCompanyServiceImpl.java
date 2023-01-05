package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.ProductCompany;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.ProductCompanyRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCompanyServiceImpl implements ProductCompanyService {


    private final BaseService baseService;
    private final ProductCompanyRepository productCompanyRepository;


    @Override
    public ApiResult<?> add(ProductCompanyReqDto productCompanyReqDto) {

        //NOMINI UNIQLIKKA TEKSHIRADI
        if (productCompanyRepository.existsByName(productCompanyReqDto.getName()) && baseService.existsWarehouse(productCompanyReqDto.getWhId()))
            throw RestException.restThrow("Maxsulot firmasi qo'shishda xatolik bo'ldi", HttpStatus.CONFLICT);

        ProductCompany productCompany = productCompanyRepository.save(ProductCompany.make(productCompanyReqDto));
        return ApiResult.successResponse("success added company id: " + productCompany.getId() + " name: " + productCompany.getName());
    }

    @Override
    public ApiResult<?> getOne(Long prodCompId) {
        ProductCompany productCompany = baseService.getProdCompanyById(prodCompId);
        return ApiResult.successResponse(ProductCompanyResDto.make(productCompany));
    }

    @Override
    public ApiResult<?> getAll(Long whId) {
//        productCompanyRepository.findById(whId);
        return null;
    }

    @Override
    public List<OptionResDto> getAllForOption(Long whId) {
        return null;
    }

    @Override
    public ApiResult<?> edit(Long compId, ProductCompanyReqDto productCompanyReqDto) {
        return null;
    }



    @Override
    public ApiResult<?> delete(Long compId) {
        if (!productCompanyRepository.existsById(compId))
            throw RestException.restThrow("Companiya mavjudmas", HttpStatus.NOT_FOUND);
        if (baseService.existsWarehouseByCompId(compId))
            throw RestException.restThrow("Companiyada Omborxonalar bor o'chira olmaysiz", HttpStatus.CONFLICT);

        productCompanyRepository.deleteById(compId);
        return ApiResult.successResponse("deleted company");
    }
}

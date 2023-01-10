package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.ProductCompanyReqDto;
import uz.ataboyev.warehouse.service.ProductCompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCompanyControllerImpl implements ProductCompanyController {

    private final ProductCompanyService productCompanyService;

    @Override
    public ApiResult<?> add(@Valid ProductCompanyReqDto productCompanyReqDto) {
        return productCompanyService.add(productCompanyReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long prodCompId) {
        return productCompanyService.getOne(prodCompId);
    }

    @Override
    public List<OptionResDto> getAll(Long whId) {
        return productCompanyService.getAll(whId);
    }

    @Override
    public List<OptionResDto> getAllForOption(Long whId) {
        return productCompanyService.getAllForOption(whId);
    }

    @Override
    public ApiResult<?> edit(Long prodCompId, ProductCompanyReqDto productCompanyReqDto) {
        return productCompanyService.edit(prodCompId, productCompanyReqDto);
    }

    @Override
    public ApiResult<?> delete(Long prodCompId) {

        return productCompanyService.delete(prodCompId);
    }
}

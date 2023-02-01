package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    @Override
    public ApiResult<?> add(@Valid CompanyReqDto companyReqDto) {
        return companyService.add(companyReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long compId) {
        return companyService.getOne(compId);
    }

    @Override
    public ApiResult<?> getAll() {
        return companyService.getAll();
    }

    @Override
    public ApiResult<?> edit(Long compId, CompanyReqDto companyReqDto) {
        return companyService.edit(compId, companyReqDto);
    }

    @Override
    public ApiResult<?> delete(Long compId) {
        return companyService.delete(compId);
    }
}

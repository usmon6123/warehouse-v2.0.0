package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;


@RequestMapping(path = RestConstant.COMPANY_CONTROLLER)
public interface CompanyController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid CompanyReqDto companyReqDto);

    @GetMapping("/get-one/{compId}")
    ApiResult<?> getOne(@PathVariable Long compId);

    @GetMapping("/get-all")
    ApiResult<?> getAll();

    @PutMapping("/edit/{compId}")
    ApiResult<?> edit(@PathVariable Long compId, @RequestBody CompanyReqDto companyReqDto);

    @DeleteMapping("/delete/{compId}")
    ApiResult<?> delete(@PathVariable Long compId);

}

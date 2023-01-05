package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.ProductCompanyReqDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;
import java.util.List;


@RequestMapping(path = RestConstant.PRODUCT_COMPANY_CONTROLLER)
public interface ProductCompanyController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid ProductCompanyReqDto productCompanyReqDto);

    @GetMapping("/get-one/{prodCompId}")
    ApiResult<?> getOne(@PathVariable Long prodCompId);

    @GetMapping("/get-all/{whId}")
    ApiResult<?> getAll(@PathVariable Long whId);

    @GetMapping("/get-all-for-option/{whId}")
    List<OptionResDto> getAllForOption(@PathVariable Long whId);

    @PutMapping("/edit/{prodCompId}")
    ApiResult<?> edit(@PathVariable Long prodCompId, @RequestBody ProductCompanyReqDto productCompanyReqDto);

    @DeleteMapping("/delete/{prodCompId}")
    ApiResult<?> delete(@PathVariable Long prodCompId);

}

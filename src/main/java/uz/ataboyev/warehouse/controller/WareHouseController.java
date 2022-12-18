package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

@RequestMapping(path = RestConstant.WAREHOUSE_CONTROLLER)
public interface WareHouseController {

    @PostMapping("/add")
    ApiResult<?> add(@RequestBody @Valid WareHouseReqDto wareHouseReqDto);

    @GetMapping("/get-one/{whId}")
    ApiResult<?> getOne(@PathVariable Long whId);

    @GetMapping("/get-all")
    ApiResult<?> getAll();

    @PutMapping("/edit/{whId}")
    ApiResult<?> edit(@PathVariable Long whId, @RequestBody WareHouseReqDto wareHouseReqDto);

    @DeleteMapping("/delete/{whId}")
    ApiResult<?> delete(@PathVariable Long whId);

}

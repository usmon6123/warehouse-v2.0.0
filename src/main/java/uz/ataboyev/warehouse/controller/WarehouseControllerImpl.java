package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.service.WarehouseService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WarehouseControllerImpl implements WareHouseController {

    private final WarehouseService warehouseService;

    @Override
    public ApiResult<?> add(@Valid WareHouseReqDto wareHouseReqDto) {
        return warehouseService.add(wareHouseReqDto);
    }

    @Override
    public ApiResult<?> getOne(Long whId) {
        return warehouseService.getOne(whId);
    }

    @Override
    public ApiResult<?> getAll() {
        return warehouseService.getAll();
    }

    @Override
    public ApiResult<?> edit(Long whId, WareHouseReqDto wareHouseReqDto) {
        return warehouseService.edit(whId,wareHouseReqDto);
    }

    @Override
    public ApiResult<?> delete(Long whId) {
        return warehouseService.delete(whId);
    }
}

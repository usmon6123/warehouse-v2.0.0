package uz.ataboyev.warehouse.service;

import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;

@Service
public interface WarehouseService {

    ApiResult<?> add(WareHouseReqDto wareHouseReqDto);

    ApiResult<?> getOne(Long whId);

    ApiResult<?> getAll();

    ApiResult<?> edit(Long whId, WareHouseReqDto wareHouseReqDto);

    ApiResult<?> delete(Long whId);
}

package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Warehouse;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.payload.WareHouseResDto;
import uz.ataboyev.warehouse.repository.CategoryRepository;
import uz.ataboyev.warehouse.repository.CompanyRepository;
import uz.ataboyev.warehouse.repository.WarehouseRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BaseService baseService;
    private final CategoryRepository categoryRepository;


    @Override
    public ApiResult<?> add(WareHouseReqDto warehouseReqDto) {

        //COMPANIYA YOQ BO'LSA HATOLIK BERADI
        if (!baseService.existsCompany(warehouseReqDto.getCompanyId()))
            return ApiResult.errorResponse("Companiya mavjudmas");

        //NOMINI UNIQLIKKA TEKSHIRADI
        if (warehouseRepository.existsByName(warehouseReqDto.getName()))
            throw RestException.restThrow("Bu nomli omborxona mavjud", HttpStatus.CONFLICT);

        Warehouse newWarehouse = Warehouse.make(warehouseReqDto);

        warehouseRepository.save(newWarehouse);

        return ApiResult.successResponse("success added warehouse id: " + newWarehouse.getId() + " name: " + newWarehouse.getName());
    }

    @Override
    public ApiResult<?> getOne(Long whId) {
        Warehouse warehouse = baseService.getWarehouseByIdElseThrow(whId);

        return ApiResult.successResponse(new WareHouseResDto(warehouse));
    }

    @Override
    public ApiResult<?> getAll() {
        List<WareHouseResDto> list = warehouseRepository.findAll().stream().map(WareHouseResDto::new).collect(Collectors.toList());
        return ApiResult.successResponse(list);
    }

    @Override
    public ApiResult<?> edit(Long whId, WareHouseReqDto wareHouseReqDto) {
        if (warehouseRepository.existsByName(wareHouseReqDto.getName()))
            throw RestException.restThrow("Bu nomli omborxona mavjud", HttpStatus.CONFLICT);

        Warehouse warehouse = baseService.getWarehouseByIdElseThrow(whId);

        warehouse.setName(wareHouseReqDto.getName());
        warehouseRepository.save(warehouse);
        return ApiResult.successResponse(new WareHouseResDto(warehouse), "success edited");
    }

    @Override
    public ApiResult<?> delete(Long whId) {
        if (!warehouseRepository.existsById(whId))
            throw RestException.restThrow("Omborxona mavjudmas", HttpStatus.NOT_FOUND);
        if (categoryRepository.existsByWarehouseId(whId))
            throw RestException.restThrow("Omborxonada maxsulotlar bor o'chira olmaysiz", HttpStatus.CONFLICT);
        warehouseRepository.deleteById(whId);
        return ApiResult.successResponse("deleted warehouse");
    }
}

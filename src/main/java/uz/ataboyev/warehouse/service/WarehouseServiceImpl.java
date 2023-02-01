package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.ProductCompany;
import uz.ataboyev.warehouse.entity.Warehouse;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.payload.WareHouseResDto;
import uz.ataboyev.warehouse.repository.ProductCompanyRepository;
import uz.ataboyev.warehouse.repository.WarehouseRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BaseService baseService;
    private final ProductCompanyRepository productCompanyRepository;


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
        if (!baseService.existsCompany(wareHouseReqDto.getCompanyId()))
            return ApiResult.errorResponse("bazani o'zgartirmoqchi bo'lgan companiya mavjudmas");

        if (warehouseRepository.existsByNameAndCompanyId(wareHouseReqDto.getName(),wareHouseReqDto.getCompanyId()))
            return ApiResult.errorResponse("Sizning Tashkilotingizda Bu nomli omborxona mavjud ");

        Warehouse warehouse = baseService.getWarehouseByIdElseThrow(whId);

        warehouse.setName(wareHouseReqDto.getName());
        warehouse.setCompanyId(wareHouseReqDto.getCompanyId());
        warehouseRepository.save(warehouse);
        return ApiResult.successResponse( "Omborxona muvaffaqiyatli o'zgartirildi");
    }

    @Override
    public ApiResult<?> delete(Long whId) {
        if (!warehouseRepository.existsById(whId))
            throw RestException.restThrow("Omborxona mavjudmas", HttpStatus.NOT_FOUND);
        if (productCompanyRepository.existsByWarehouseId(whId))
            throw RestException.restThrow("Omborxonada maxsulotlar bor o'chira olmaysiz", HttpStatus.CONFLICT);
        warehouseRepository.deleteById(whId);
        return ApiResult.successResponse("deleted warehouse");
    }
}

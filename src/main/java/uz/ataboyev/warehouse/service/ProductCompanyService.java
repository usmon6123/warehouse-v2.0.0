package uz.ataboyev.warehouse.service;

import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.payload.OptionResDto;
import uz.ataboyev.warehouse.payload.ProductCompanyReqDto;

import java.util.List;

@Service
public interface ProductCompanyService {

    ApiResult<?> add(ProductCompanyReqDto productCompanyReqDto);

    ApiResult<?> getOne(Long prodCompId);

    List<OptionResDto> getAll(Long whId);

    List<OptionResDto> getAllForOption(Long whId);

    ApiResult<?> edit(Long compId, ProductCompanyReqDto productCompanyReqDto);

    ApiResult<?> delete(Long prodCompId);
}

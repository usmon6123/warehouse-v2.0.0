package uz.ataboyev.warehouse.service;

import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;

@Service
public interface CompanyService {
    ApiResult<?> add(CompanyReqDto companyReqDto);

    ApiResult<?> getOne(Long compId);

    ApiResult<?> getAll();

    ApiResult<?> edit(Long compId, CompanyReqDto companyReqDto);

    ApiResult<?> delete(Long compId);

}

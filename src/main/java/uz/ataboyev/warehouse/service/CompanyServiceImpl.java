package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.CompanyReqDto;
import uz.ataboyev.warehouse.payload.CompanyResDto;
import uz.ataboyev.warehouse.repository.CompanyRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {


    private final BaseService baseService;
    private final CompanyRepository companyRepository;


    @Override
    public ApiResult<?> add(CompanyReqDto companyReqDto) {

        //NOMINI UNIQLIKKA TEKSHIRADI
        if (companyRepository.existsByName(companyReqDto.getName()))
            throw RestException.restThrow("Bu nomli omborxona mavjud", HttpStatus.CONFLICT);

        Company company = companyRepository.save(new Company(companyReqDto.getName()));

        return ApiResult.successResponse("success added company id: " + company.getId() + " name: " + company.getName());
    }

    @Override
    public ApiResult<?> getOne(Long compId) {
        Company company = baseService.getCompanyById(compId);
        return ApiResult.successResponse(new CompanyResDto(company));
    }

    @Override
    public ApiResult<?> getAll() {
        List<CompanyResDto> list = companyRepository.findAll().stream().map(CompanyResDto::new).collect(Collectors.toList());
        return ApiResult.successResponse(list);
    }

    @Override
    public ApiResult<?> edit(Long compId, CompanyReqDto companyReqDto) {

        //todo queryni to'g'irlash kerak
        if (companyRepository.existsByNameAndIdNot(companyReqDto.getName(),compId))
            throw RestException.restThrow("Bu nomli Companiya mavjud", HttpStatus.CONFLICT);

        Company company = baseService.getCompanyById(compId);

        company.setName(companyReqDto.getName());
        companyRepository.save(company);

        return ApiResult.successResponse(new CompanyResDto(company), "success edited");
    }

    @Override
    public ApiResult<?> delete(Long compId) {
        if (!companyRepository.existsById(compId))
            throw RestException.restThrow("Companiya mavjudmas", HttpStatus.NOT_FOUND);
        if (baseService.existsWarehouseByCompId(compId))
            throw RestException.restThrow("Companiyada Omborxonalar bor o'chira olmaysiz", HttpStatus.CONFLICT);

        companyRepository.deleteById(compId);
        return ApiResult.successResponse("deleted company");
    }
}

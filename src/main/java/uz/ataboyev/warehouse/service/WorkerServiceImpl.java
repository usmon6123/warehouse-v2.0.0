package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WorkerHistorySalaryResDto;
import uz.ataboyev.warehouse.payload.WorkerSalaryReqDto;
import uz.ataboyev.warehouse.payload.WorkersTotalSalaryResDto;
import uz.ataboyev.warehouse.repository.WorkerRepository;
import uz.ataboyev.warehouse.service.base.BaseService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final BaseService baseService;
    private final WorkerRepository workerRepository;

    @Override
    public ApiResult<?> addSalary(WorkerSalaryReqDto workerSalaryReqDto) {
        return null;
    }

    @Override
    public WorkerHistorySalaryResDto workerHistorySalary(Timestamp startDate, Timestamp endDate, Long workerId) {
        return null;
    }

    @Override
    public List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(Timestamp startDate, Timestamp endDate) {
        return null;
    }

    @Override
    public List<WorkersTotalSalaryResDto> getAllBalanceSalary(Timestamp startDate, Timestamp endDate) {
        return null;
    }
}

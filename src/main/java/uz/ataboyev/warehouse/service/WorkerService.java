package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.*;

import java.sql.Timestamp;
import java.util.List;

public interface WorkerService {

    ApiResult<?> addSalary(WorkerSalaryReqDto workerSalaryReqDto);

    WorkerSalaryResDto workerHistorySalary(Long startDate, Long endDate, Long workerId);

    List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(Timestamp startDate, Timestamp endDate);

    List<WorkersTotalSalaryResDto> getAllBalanceSalary(Long startDate, Long endDate);

}

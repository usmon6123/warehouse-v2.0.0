package uz.ataboyev.warehouse.service;

import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WorkerHistorySalaryResDto;
import uz.ataboyev.warehouse.payload.WorkerSalaryReqDto;
import uz.ataboyev.warehouse.payload.WorkersTotalSalaryResDto;

import java.sql.Timestamp;
import java.util.List;

public interface WorkerService {

    ApiResult<?> addSalary(WorkerSalaryReqDto workerSalaryReqDto);

    WorkerHistorySalaryResDto workerHistorySalary(Timestamp startDate, Timestamp endDate, Long workerId);

    List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(Timestamp startDate, Timestamp endDate);

    List<WorkersTotalSalaryResDto> getAllBalanceSalary(Timestamp startDate, Timestamp endDate);

}

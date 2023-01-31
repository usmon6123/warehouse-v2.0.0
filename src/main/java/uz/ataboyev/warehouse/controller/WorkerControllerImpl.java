package uz.ataboyev.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.service.WorkerService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerControllerImpl implements WorkerController {

    private final WorkerService workerService;

    @Override
    public ApiResult<?> addSalary(@Valid WorkerSalaryReqDto workerSalaryReqDto) {
        return workerService.addSalary(workerSalaryReqDto);
    }

    @Override
    public WorkerSalaryResDto workerHistorySalary(Long startDate,Long endDate,Long workerId) {
        return workerService.workerHistorySalary(startDate,endDate,workerId);
    }

    @Override
    public List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(Long startDate, Long endDate) {
        return workerService.getAllWorkersHistorySalary(startDate,endDate);
    }

    @Override
    public List<WorkersTotalSalaryResDto> getAllBalanceSalary(Long startDate, Long endDate) {
        return workerService.getAllBalanceSalary(startDate,endDate);
    }
}

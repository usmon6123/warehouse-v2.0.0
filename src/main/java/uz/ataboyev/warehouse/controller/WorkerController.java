package uz.ataboyev.warehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.ApiResult;
import uz.ataboyev.warehouse.payload.WorkerHistorySalaryResDto;
import uz.ataboyev.warehouse.payload.WorkerSalaryReqDto;
import uz.ataboyev.warehouse.payload.WorkersTotalSalaryResDto;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

import java.sql.Timestamp;
import java.util.List;

import static uz.ataboyev.warehouse.utils.AppConstant.*;

@RequestMapping(path = RestConstant.WORKER_CONTROLLER)
public interface WorkerController {

    @PostMapping("/add-salary")
    ApiResult<?> addSalary(@RequestBody @Valid WorkerSalaryReqDto workerSalaryReqDto);

    @GetMapping("/get-one/{workerId}")
    WorkerHistorySalaryResDto workerHistorySalary(@RequestParam(defaultValue = DEFAULT_START_DATE)Timestamp startDate,
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Timestamp endDate,
                                     @PathVariable Long workerId);

    @GetMapping("/get-all")
    List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(@RequestParam(defaultValue = DEFAULT_START_DATE)Timestamp startDate,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Timestamp endDate);

    @GetMapping("/get-all-balance-salary")
    List<WorkersTotalSalaryResDto> getAllBalanceSalary(@RequestParam(defaultValue = DEFAULT_START_DATE)Timestamp startDate,
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Timestamp endDate);




}

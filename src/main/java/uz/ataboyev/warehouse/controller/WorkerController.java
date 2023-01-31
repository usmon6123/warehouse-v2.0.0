package uz.ataboyev.warehouse.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.utils.RestConstant;

import javax.validation.Valid;

import java.sql.Timestamp;
import java.util.List;

import static uz.ataboyev.warehouse.utils.AppConstant.*;

@RequestMapping(path = RestConstant.WORKER_CONTROLLER)
public interface WorkerController {

    @PostMapping("/add-salary")
    ApiResult<?> addSalary(@RequestBody @Valid WorkerSalaryReqDto workerSalaryReqDto);

    //BITTA ISHCHINING KIRITILGAN VAQT ORALIG'IDA OLGAN OYLIKLARINI  VA JAMI OLGAN SUMMASINI QAYTARADIGAN YO'L
    @GetMapping("/get-one/{workerId}")
    WorkerSalaryResDto workerHistorySalary(@RequestParam (defaultValue = DEFAULT_START_DATE)Long startDate,
                                           @RequestParam (defaultValue = DEFAULT_END_DATE) Long endDate,
                                           @PathVariable Long workerId);

    @GetMapping("/get-all")
    List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(@RequestParam (defaultValue = DEFAULT_START_DATE)Long startDate,
                                                               @RequestParam (defaultValue = DEFAULT_END_DATE) Long endDate);

    @GetMapping("/get-all-balance-salary")
    List<WorkersTotalSalaryResDto> getAllBalanceSalary(@RequestParam (defaultValue = DEFAULT_START_DATE)Long startDate,
                                                       @RequestParam (defaultValue = DEFAULT_END_DATE) Long endDate);




}

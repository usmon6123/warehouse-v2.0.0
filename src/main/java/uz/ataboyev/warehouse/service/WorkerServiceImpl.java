package uz.ataboyev.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.WorkerSalary;
import uz.ataboyev.warehouse.payload.*;
import uz.ataboyev.warehouse.repository.WorkerSalaryRepository;
import uz.ataboyev.warehouse.service.base.BaseService;
import uz.ataboyev.warehouse.utils.AppConstant;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_END_DATE;
import static uz.ataboyev.warehouse.utils.AppConstant.DEFAULT_START_DATE;

@RestController
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final BaseService baseService;
    private final WorkerSalaryRepository workerSalaryRepository;

    @Override
    public ApiResult<?> addSalary(WorkerSalaryReqDto workerSalaryReqDto) {
        baseService.getWorkerById(workerSalaryReqDto.getWorkerId());
        WorkerSalary workerSalary = WorkerSalary.make(workerSalaryReqDto);
        workerSalaryRepository.save(workerSalary);
        return ApiResult.successResponse("Ishchiga maosh muvafaqiyatli berildi");
    }

    @Override
    public WorkerSalaryResDto workerHistorySalary(Long startDateTime,
                                                  Long endDateTime,
                                                  Long workerId) {
        Timestamp startDate = new Timestamp(startDateTime);
        Timestamp endDate = new Timestamp(endDateTime);

        Client worker = baseService.getWorkerById(workerId);

        List<OneWorkerSalary> salaries = workerSalaryRepository.getWorkerSalariesByWorkerId(workerId, startDate, endDate);
        List<PriceAndType> workerSum = workerSalaryRepository.getWorkerSum(workerId, startDate, endDate);

        return WorkerSalaryResDto.make(worker, salaries, workerSum);
    }

    @Override
    public List<WorkerHistorySalaryResDto> getAllWorkersHistorySalary(Timestamp startDate, Timestamp endDate) {
        return null;
    }

    @Override
    public List<WorkersTotalSalaryResDto> getAllBalanceSalary(Long startDate, Long endDate) {

        List<GetAllWorkersTotalSalaries> allWorkersTotalSalaries = workerSalaryRepository.getAllWorkersTotalSalaries();

        return allWorkersTotalSalaries.stream().map(WorkersTotalSalaryResDto::make).collect(Collectors.toList());
    }

    private Timestamp generateLongToTimestamp(Long longDate) {
        return new Timestamp(longDate);


    }
}

package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.WorkerSalary;
import uz.ataboyev.warehouse.payload.GetAllWorkersTotalSalaries;
import uz.ataboyev.warehouse.payload.OneWorkerSalary;
import uz.ataboyev.warehouse.payload.PriceAndType;
import uz.ataboyev.warehouse.payload.WorkerHistorySalary;

import java.sql.Timestamp;
import java.util.List;

public interface WorkerSalaryRepository extends JpaRepository<WorkerSalary,Long> {

        @Query(value = "select ws.created_at    as date, " +
                "       ws.description          as description, " +
                "       ws.sum                  as sum, " +
                "       ws.type                 as type " +
                "from worker_salary ws " +
                "where (ws.worker_id =  :workerId " +
                "  and ws.created_at >= :startDate " +
                "  and ws.created_at <= :endDate) " +
                "order by ws.created_at desc ", nativeQuery = true)
        List<OneWorkerSalary> getWorkerSalariesByWorkerId(@Param("workerId") Long workerId,
                                                          @Param("startDate")Timestamp startDate,
                                                          @Param("endDate") Timestamp endDate);


        @Query(value = "select sum(ws.sum) as price, " +
                "       ws.type     as type " +
                "from worker_salary ws " +
                "where (ws.worker_id =    :workerId " +
                "    and ws.created_at >= :startDate " +
                "    and ws.created_at <= :endDate) " +
                "group by ws.type",nativeQuery = true)
        List<PriceAndType> getWorkerSum(@Param("workerId") Long workerId,
                                        @Param("startDate") Timestamp startDate,
                                        @Param("endDate") Timestamp endDate);

        @Query(value ="select ws.updated_at  as date, " +
                "       ws.worker_id   as workerId, " +
                "       c.name         as workerName, " +
                "       ws.sum         as sum, " +
                "       ws.type        as type, " +
                "       ws.description as description " +
                "from worker_salary ws " +
                "         inner join client c on c.id = ws.worker_id " +
                "where ws.updated_at >= :startDate and ws.updated_at <= :endDate " +
                "order by date desc",nativeQuery = true)
        List<WorkerHistorySalary> getWorkerSalaries(@Param("startDate") Timestamp startDate,
                                                    @Param("endDate") Timestamp endDate);



    @Query(value = "with tempSum as (select ws.worker_id, " +
                "                        sum(ws.sum) " +
                "                 from worker_salary ws " +
                "                 where type = 'SUM' " +
                "                 group by ws.worker_id), " +
                "     temDollar as (select ws.worker_id " +
                "                        , sum(ws.sum) " +
                "                   from worker_salary ws " +
                "                   where type = 'DOLLAR' " +
                "                   group by ws.worker_id), " +
                "     head as (select ws.worker_id, " +
                "                     c.name," +
                "                     ws.updated_at as date " +
                "              from worker_salary ws " +
                "                       inner join client c on c.id = ws.worker_id " +
                "              group by ws.worker_id, c.name, date ) " +
                "select distinct td.worker_id as workerId, " +
                "       ws.name      as workerName, " +
                "       td.sum       as totalDollar, " +
                "       ts.sum       as totalSum " +
                "from head ws " +
                "         left join tempSum ts on ts.worker_id = ws.worker_id " +
                "         left join temDollar td on td.worker_id = ws.worker_id " +
                "where ws.date >= :startDate and ws.date <= :endDate", nativeQuery = true)
        List<GetAllWorkersTotalSalaries> getAllWorkersTotalSalaries( @Param("startDate") Timestamp startDate,
                                                                     @Param("endDate") Timestamp endDate);
}

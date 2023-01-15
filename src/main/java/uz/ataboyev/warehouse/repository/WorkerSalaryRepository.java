package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.WorkerSalary;
import uz.ataboyev.warehouse.payload.GetAllWorkersTotalSalaries;
import uz.ataboyev.warehouse.payload.OneWorkerSalary;
import uz.ataboyev.warehouse.payload.PriceAndType;

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


        @Query(value = "SELECT ws.worker_id as workerId, " +
                "       c.name              as workerName, " +
                "       sum(ws.sum)         as price, " +
                "       ws.type             as currencyType " +
                "FROM worker_salary ws " +
                "         inner join client c on c.id = ws.worker_id " +
                "group by ws.type, ws.worker_id, c.name", nativeQuery = true)
        List<GetAllWorkersTotalSalaries> getAllWorkersTotalSalaries();
}

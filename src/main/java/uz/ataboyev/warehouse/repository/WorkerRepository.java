package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ataboyev.warehouse.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
}

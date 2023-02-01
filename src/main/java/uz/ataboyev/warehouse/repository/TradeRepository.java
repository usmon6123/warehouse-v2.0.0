package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ataboyev.warehouse.entity.Trade;
@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {

}

package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalance;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndName(String phoneNumber, String name);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsById(Long id);



    @Query(value = "select  c.client_type as clientType, " +
            "CAST (c.id as varchar) as clientId, "+
            "c.name as clientName, " +
            "cast(SUM(o.order_price_dollar) as numeric ) as balanceDollar," +
            "cast(SUM(o.order_price_sum) as numeric )as balanceSum from client c " +
            "inner join orders o on c.id = o.client_id " +
            "where (o.warehouse_id = :warehouseId) " +
            "group by c.name,c.id, c.client_type order by c.name",nativeQuery = true)
    List<ClientBalance> getALLClientBalance(@Param("warehouseId")Long warehouseId);



    Optional<Client> findByClientType(Type clientType);

//    @SqlResultSetMapping(name = "mapClientHistoryDto",
//            classes = @ConstructorResult(targetClass = ClientHistoryDto.class,columns = )
//                        )
//    ClientHistoryDto clientHistoryDto(@Param("clientId")Long clientId);



}

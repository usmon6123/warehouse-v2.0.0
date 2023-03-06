package uz.ataboyev.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalance;
import uz.ataboyev.warehouse.payload.clientDtos.ClientBalanceEqualNull;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndName(String phoneNumber, String name);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    boolean existsById(Long id);
    @Query(value = "select * from client c where c.id = :workerId and c.client_type = 'WORKER'",nativeQuery = true)
    Optional<Client> getWorkerById(@Param("workerId") Long workerId);



    @Query(value = "select c.client_type                       as clientType, " +
            "       CAST(c.id as varchar)                      as clientId, " +
            "       c.name                                     as clientName, " +
            "       cast(SUM(o.order_price_dollar) as numeric) as balanceDollar, " +
            "       cast(SUM(o.order_price_sum) as numeric)    as balanceSum " +
            "from client c " +
            "         left join orders o on c.id = o.client_id " +
            "where c.client_type='CONSUMER' or c.client_type = 'COSTUMER' " +
            "group by c.name, c.id, c.client_type " +
            "order by c.name ",nativeQuery = true)
    List<ClientBalance> getALLClientBalance(@Param("warehouseId")Long warehouseId);



    Optional<Client> findByClientType(Type clientType);
    List<Client> findAllByClientType(Type clientType);

    @Query(value = "select  c.client_type as clientType, " +
            "                     CAST (c.id as varchar) as clientId, " +
            "                     c.name as clientName from client c " +
            "             inner join orders o on c.id <> o.client_id " +
            "             where (o.warehouse_id = :warehouseId) " +
            "             order by c.name",nativeQuery = true)
    List<ClientBalanceEqualNull> getClientsBalanceEqualsNull(Long warehouseId);


    @Query(value = "select * from client c where c.client_type = 'WORKER' ", nativeQuery = true)
    List<Client>getAllWorkers();

//    @SqlResultSetMapping(name = "mapClientHistoryDto",
//            classes = @ConstructorResult(targetClass = ClientHistoryDto.class,columns = )
//                        )
//    ClientHistoryDto clientHistoryDto(@Param("clientId")Long clientId);



}

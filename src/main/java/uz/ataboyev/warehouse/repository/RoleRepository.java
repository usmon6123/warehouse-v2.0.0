//package uz.ataboyev.warehouse.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import uz.ataboyev.warehouse.entity.Role;
//
//import java.util.Optional;
//
//public interface RoleRepository extends JpaRepository<Role,Long> {
//
//    Optional<Role> findByName(String name);
//    boolean existsByName(String name);
//
//    void deleteByName(String name);
//}

//package uz.ataboyev.warehouse.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import uz.ataboyev.warehouse.entity.User;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public interface UserRepository extends JpaRepository<User, UUID> {
//
//    Optional<User> findByUsername(String username);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByRoleId(Long role_id);
//
//    List<User> findAllByRoleId(Long role_id);
//}

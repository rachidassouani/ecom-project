package ma.enset.pfe_ecommerce.repositories;

import ma.enset.pfe_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRolesRoleName(String roleName);
    boolean existsUserByEmail(String email);
}

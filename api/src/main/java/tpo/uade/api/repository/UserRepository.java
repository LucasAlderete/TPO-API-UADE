package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.User;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

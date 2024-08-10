package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.database.UserDB;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDB, Long> {
    Optional<UserDB> findByUsername(String username);
}

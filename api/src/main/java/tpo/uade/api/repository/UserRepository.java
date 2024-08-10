package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.database.UserDB;

public interface UserRepository extends JpaRepository<UserDB, Long> {
}

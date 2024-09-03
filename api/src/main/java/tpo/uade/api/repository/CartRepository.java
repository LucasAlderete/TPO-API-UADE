package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.CartModel;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findByUserId(Long userId);
}
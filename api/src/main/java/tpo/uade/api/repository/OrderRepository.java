package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.OrderModel;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    Optional<OrderModel> findByUser_UserId(Long userId);
}

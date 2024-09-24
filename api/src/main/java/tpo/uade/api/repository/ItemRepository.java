package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpo.uade.api.model.ItemModel;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    Optional<ItemModel> findByCartIdAndProductId(Long cartId, Integer productId);
}

package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.uade.api.model.ProductModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findById(int productId);

    Optional<ProductModel> findBySecureId(String secureId);

    Optional<?> deleteBySecureId(String secureId);

    List<ProductModel> findByIdIn(List<Integer> ids);
}

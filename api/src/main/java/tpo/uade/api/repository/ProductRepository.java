package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.uade.api.model.ProductModel;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{
    Optional<ProductModel> findById(long id);
    Optional<ProductModel> findBySecureId(String secureId);
    void deleteBySecureId(String secureId);
}

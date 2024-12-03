package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.uade.api.model.CategoryModel;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    Optional<CategoryModel> findById(int categoryId);
    Optional<CategoryModel> findByName(String name);
}

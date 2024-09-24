package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.model.ProductModel;

import java.util.List;


@Repository
public interface NavigationRespository extends JpaRepository<NavigationModel, Integer> {
    List<NavigationModel> findByUser_UserId(Long userId);
}
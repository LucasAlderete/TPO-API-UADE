package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.uade.api.model.NavigationModel;


@Repository
public interface NavigationRespository extends JpaRepository<NavigationModel, Integer> {
}
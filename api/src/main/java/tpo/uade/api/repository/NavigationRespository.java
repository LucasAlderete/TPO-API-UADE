package tpo.uade.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpo.uade.api.model.NavigationModel;

import java.util.List;

@Repository
public interface NavigationRespository extends JpaRepository<NavigationModel, Long> {
    List<NavigationModel> findByUser_UserId(Long userId);
    List<NavigationModel> findByUser_UserIdOrderByViewedAtDesc(Long userId);

    default List<NavigationModel> findLastFiveByUserId(Long userId) {
        return findByUser_UserIdOrderByViewedAtDesc(userId)
                .stream()
                .limit(5)
                .toList();
    }
}
package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.mapper.NavigationMapper;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.NavigationRespository;
import tpo.uade.api.service.INavigationService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NavigationService implements INavigationService {

    private final NavigationMapper navigationMapper;
    private final NavigationRespository navigationRepository;
    private final UserService userService;

    @Override
    public void save(NavigationDto navigationDto) {
        NavigationModel entity = navigationMapper.mapToDatabaseEntity(navigationDto);
        UserModel userLoggedModel = userService.getUserModelByUsername();

        if (userLoggedModel == null) {
            return;
        }
        entity.setUser(userLoggedModel);
        entity.setViewedAt(LocalDateTime.now());
        navigationRepository.save(entity);
    }

    @Override
    public List<NavigationDto> getLastFiveVisited() {
        UserModel userLoggedModel = userService.getUserModelByUsername();
        if (userLoggedModel == null) {
            return List.of();
        }

        List<NavigationModel> lastFiveEntities = navigationRepository.findLastFiveByUserId(userLoggedModel.getUserId());
        return lastFiveEntities.stream()
                .map(navigationMapper::mapFromDatabaseEntity)
                .toList();
    }

}
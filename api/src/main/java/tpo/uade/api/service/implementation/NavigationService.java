package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.mapper.NavigationMapper;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.repository.NavigationRespository;
import tpo.uade.api.service.INavigationService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NavigationService implements INavigationService {

    private final NavigationMapper navigationMapper;
    private final NavigationRespository navigationRepository;

    @Override
    public void save(NavigationDto navigationDto) {
        NavigationModel entity = navigationMapper.mapToDatabaseEntity(navigationDto);
        entity.setViewedAt(LocalDateTime.now());
        navigationRepository.save(entity);
    }
}
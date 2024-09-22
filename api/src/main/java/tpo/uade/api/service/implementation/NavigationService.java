package tpo.uade.api.service.implementation;

import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.mapper.NavigationMapper;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.repository.NavigationRespository;
import tpo.uade.api.service.INavigationService;

public class NavigationService implements INavigationService {

    private final NavigationMapper navigationMapper;
    private final NavigationRespository navigationRepository;

    public NavigationService(NavigationMapper navigationMapper, NavigationRespository navigationRepository) {
        this.navigationMapper = navigationMapper;
        this.navigationRepository = navigationRepository;
    }

    @Override
    public void save(NavigationDto navigationDto) {
        NavigationModel entity = navigationMapper.mapToDatabaseEntity(navigationDto);
        navigationRepository.save(entity);
    }

}

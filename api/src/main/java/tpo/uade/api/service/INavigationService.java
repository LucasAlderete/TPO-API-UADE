package tpo.uade.api.service;

import tpo.uade.api.dto.NavigationDto;

import java.util.List;

public interface INavigationService {
    void save (NavigationDto navigationDto);
    List<NavigationDto> getLastFiveVisited();
}

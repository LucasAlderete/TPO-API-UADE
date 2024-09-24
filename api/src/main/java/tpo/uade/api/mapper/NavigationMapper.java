package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.model.NavigationModel;

public interface NavigationMapper {

    NavigationDto mapFromDatabaseEntity (NavigationModel navigationModel);

    NavigationModel mapToDatabaseEntity (NavigationDto navigationModel);

}
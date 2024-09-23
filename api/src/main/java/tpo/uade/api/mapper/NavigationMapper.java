package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.model.NavigationModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface NavigationMapper {

    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    NavigationDto mapFromDatabaseEntity (NavigationModel navigationModel);

    NavigationModel mapToDatabaseEntity (NavigationDto navigationModel);

}
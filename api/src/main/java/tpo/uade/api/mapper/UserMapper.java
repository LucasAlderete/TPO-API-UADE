package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import tpo.uade.api.dto.UserDto;
import tpo.uade.api.model.UserModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserMapper {

    UserDto mapFromDatabaseEntity (UserModel userModel);

    UserModel mapToDatabaseEntity (UserDto userDTO);
}
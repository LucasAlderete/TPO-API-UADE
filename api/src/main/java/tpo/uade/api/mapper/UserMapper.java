package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import tpo.uade.api.dto.UserDto;
import tpo.uade.api.model.UserModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "passwordMapping")
    UserDto mapFromDatabaseEntity (UserModel userModel);

    UserModel mapToDatabaseEntity (UserDto userDTO);

    @Named("passwordMapping")
    default String passwordMapping (String password) {
        return "";
    }
}
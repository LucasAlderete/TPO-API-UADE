package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import tpo.uade.api.dto.user.UserRequestDto;
import tpo.uade.api.dto.user.UserResponseDto;
import tpo.uade.api.model.RoleEnum;
import tpo.uade.api.model.UserModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "role", qualifiedByName = "mapEnum")
    UserResponseDto mapFromDatabaseEntity (UserModel userModel);

    @Named("mapEnum")
    default String mapValue(final RoleEnum role) {
        return role.toString();
    }

    UserModel mapToDatabaseEntity (UserRequestDto userRequestDTO);
}
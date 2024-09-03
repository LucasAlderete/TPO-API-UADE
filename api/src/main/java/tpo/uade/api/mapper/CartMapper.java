package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.model.CartModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {ItemMapper.class})
public interface CartMapper {
    CartDto toDto(CartModel cartModel);
    CartModel toEntity(CartDto cartDto);
}

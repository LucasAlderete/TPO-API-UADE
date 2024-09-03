package tpo.uade.api.mapper;

import org.mapstruct.*;
import tpo.uade.api.dto.ItemDto;
import tpo.uade.api.model.ItemModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {
    @Mapping(source = "product.name", target = "product")
    ItemDto toDto(ItemModel itemModel);

    @Mapping(source = "product", target = "product.name")
    ItemModel toEntity(ItemDto itemDto);
}

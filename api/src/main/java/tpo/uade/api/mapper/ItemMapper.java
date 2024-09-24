package tpo.uade.api.mapper;

import org.mapstruct.*;
import tpo.uade.api.dto.ItemDto;
import tpo.uade.api.model.ItemModel;
import tpo.uade.api.model.OrderItemModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface ItemMapper {
    @Mapping(source = "product.name", target = "product")
    ItemDto toDto(ItemModel itemModel);

    @Mapping(source = "product", target = "product.name")
    ItemModel toEntity(ItemDto itemDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItemModel mapItemToOrderItem(ItemModel itemModel);
}

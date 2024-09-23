package tpo.uade.api.mapper;

import org.mapstruct.*;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.model.OrderModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderDto toDto(OrderModel orderModel);

    //TODO: no fue necesario hacer método toEntity()
}

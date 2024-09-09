package tpo.uade.api.mapper;

import org.springframework.stereotype.Component;
import tpo.uade.api.dto.OrderItemDto;
import tpo.uade.api.model.OrderItemModel;

@Component
public class OrderItemMapper {
    public OrderItemDto toDto(OrderItemModel orderItem){
        return OrderItemDto.builder()
                .price(orderItem.getPrice())
                .product(orderItem.getProduct().getName()) //TODO a revisar
                .quantity(orderItem.getQuantity())
                .build();
    }
}

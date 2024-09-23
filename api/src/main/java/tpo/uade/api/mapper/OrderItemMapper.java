package tpo.uade.api.mapper;

import org.springframework.stereotype.Component;
import tpo.uade.api.dto.OrderItemDto;
import tpo.uade.api.model.OrderItemModel;

@Component
public class OrderItemMapper {

    public OrderItemDto toDto(OrderItemModel orderItem){
      return OrderItemDto.builder()
              .quantity(orderItem.getQuantity())
              .product(orderItem.getProduct().getName())
              .price(orderItem.getPrice())
              .build();
    }

    //TODO: no fue necesario hacer método toEntity()

}

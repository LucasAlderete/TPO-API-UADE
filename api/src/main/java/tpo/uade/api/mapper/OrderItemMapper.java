package tpo.uade.api.mapper;

import org.springframework.stereotype.Component;
import tpo.uade.api.dto.OrderItemDto;
import tpo.uade.api.model.OrderItemModel;

@Component
public class OrderItemMapper {

    public OrderItemDto toDto(OrderItemModel orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setProduct(orderItem.getProduct().getName());
        return orderItemDto;
    }

    //TODO: no fue necesario hacer método toEntity()

}

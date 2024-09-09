package tpo.uade.api.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tpo.uade.api.dto.InfoUserDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.model.OrderModel;
import tpo.uade.api.model.UserModel;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MyProfileMapper {
    private OrderItemMapper orderItemMapper;

    public InfoUserDto toInfoUserDto(UserModel user){
        return InfoUserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .build();
    }
    public OrderDto toOrderDto(OrderModel order){
        return OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .items(order.getItems().stream().map(orderItemMapper::toDto).collect(Collectors.toList()))
                .total(order.getTotal())
                .build();
    }

}

package tpo.uade.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutsByUserDto {
    private List<OrderDto> checkouts;
}

package tpo.uade.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyProfileDto {
    private InfoUserDto user;
    private CheckoutsByUserDto checkouts;
}

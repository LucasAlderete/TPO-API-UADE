package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto implements Serializable {
    @NotBlank(message = "product must not be null nor empty")
    private String product; //TODO: no coincide con el entity

    @NotBlank(message = "quantity must not be null nor empty")
    private Integer quantity;

    @NotBlank(message = "price must not be null nor empty")
    private Integer price;
}
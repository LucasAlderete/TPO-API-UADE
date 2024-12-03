package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {

    @NotBlank(message = "product must not be null nor empty")
    private String name;

    private String image;

    @NotBlank(message = "quantity must not be null nor empty")
    private Integer quantity;

    @NotBlank(message = "price must not be null nor empty")
    private Double price;
}
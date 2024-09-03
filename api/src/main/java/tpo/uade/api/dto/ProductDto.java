package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID productId;
    @NotBlank(message = "product name must not be null nor empty")
    private String name;
    @NotBlank(message = "product description must not be null nor empty")
    private String description;
    private int price;
    private List<String> images;
    @NotBlank(message = "product category must not be null nor empty")
    private String category;
    @PositiveOrZero(message = "Stock must be positive or zero")
    private int stock;
}

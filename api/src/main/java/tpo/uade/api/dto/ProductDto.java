package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private int productId;
    @NotBlank(message = "product name must not be null nor empty")
    private String name;
    private Double price;
    private String categoryName;
    private String urlImage;
    private String description;

    private double price;
    private List<String> images;

    @NotBlank(message = "product category must not be null nor empty")
    private String categoryName;

    @PositiveOrZero(message = "Stock must be positive or zero")
    private int stock;
    private String additionalInformation;
    private boolean highlighted;
}

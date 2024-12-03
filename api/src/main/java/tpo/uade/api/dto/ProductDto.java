package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    private String secureId;
    private Long id;

    @NotBlank(message = "product name must not be null nor empty")
    private String name;

    private String description;

    private Double price;

    private List<String> images;

    @NotBlank(message = "product category must not be null nor empty")
    private String categoryName;

    @PositiveOrZero(message = "Stock must be positive or zero")
    private int stock;

    private String additionalInformation;

    private boolean highlighted;
}
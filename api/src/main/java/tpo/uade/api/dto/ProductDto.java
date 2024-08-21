package tpo.uade.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto implements Serializable {
    private int productId;
    private String name;
    private BigDecimal price;
}

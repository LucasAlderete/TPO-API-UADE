package tpo.uade.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private int productId;
    private String name;
    private BigDecimal price;
    private String categoryName;
    private String urlImage;
    //Si usamos mas de una imagen dejamos el list
    //private List<String> urlImages;
}

package tpo.uade.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private int productId;
    private String name;
    private double price;
    private String categoryName;
    private String urlImage;
    private String description;
    private String additionalInformation;
    private int stock;
    private boolean bookmarked;
}

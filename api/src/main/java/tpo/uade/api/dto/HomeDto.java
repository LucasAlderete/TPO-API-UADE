package tpo.uade.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class HomeDto implements Serializable{

    private Map<String, List<ProductDto>> products;

    private List<ProductDto> recentlyViewedProducts;

    private List<ProductDto> featuredProducts;

    private List<ProductDto> recommendedProducts;
}
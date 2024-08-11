package tpo.uade.api.dto;

import java.util.List;

import lombok.Data;
import tpo.uade.api.model.FeaturedProductModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.RecentlyViewedModel;

@Data
public class HomeDto {
    private List<ProductModel> products;
    private List<RecentlyViewedModel> recentlyViewedProducts;
    private List<FeaturedProductModel> featuredProducts;

    public HomeDto() {
    }
    
    public HomeDto(List<ProductModel> products, List<RecentlyViewedModel> recentlyViewedProducts, List<FeaturedProductModel> featuredProducts) {
        this.products = products;
        this.recentlyViewedProducts = recentlyViewedProducts;
        this.featuredProducts = featuredProducts;
    }
}

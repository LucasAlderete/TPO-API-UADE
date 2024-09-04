package tpo.uade.api.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import tpo.uade.api.model.FeaturedProductModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.RecentlyViewedModel;

@Data
public class HomeDto implements Serializable {
    private List<ProductModel> products;
    private List<RecentlyViewedModel> recentlyViewedProducts;
    private List<FeaturedProductModel> featuredProducts;
}

package tpo.uade.api.service;

import java.util.List;
import java.util.UUID;

import tpo.uade.api.model.FeaturedProductModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.RecentlyViewedModel;

public class MockProductHelper {
    
    public static ProductModel get() {
        return new ProductModel(1L,"Product Name", 100D, "algo", 3,"mochila");
    }
    
    public static List<ProductModel> getList() {
        return List.of(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        );
    }
    

    public static RecentlyViewedModel getRecentlyViewed() {
        return new RecentlyViewedModel(10, "Recently Viewed Product", 200D);
    }

    public static List<RecentlyViewedModel> getListRecentlyViewed() {
        return List.of(getRecentlyViewed(), getRecentlyViewed(), getRecentlyViewed());
    }

    public static FeaturedProductModel getFeaturedProduct() {
        return new FeaturedProductModel(100, "Featured Product", 5000D);
    }
    
    public static List<FeaturedProductModel> getListFeaturedProduct() {
        return List.of(getFeaturedProduct(), getFeaturedProduct());
    }
    

}

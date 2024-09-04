package tpo.uade.api.model;

import java.io.Serializable;

public class RecentlyViewedModel extends ProductModel implements Serializable {

    public RecentlyViewedModel(int productId, String name, double price) {
        super(productId, name, price);
    }
    
}

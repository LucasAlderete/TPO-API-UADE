package tpo.uade.api.model;

import java.io.Serializable;

public class FeaturedProductModel extends ProductModel implements Serializable {

    public FeaturedProductModel(int productId, String name, double price) {
        super(productId, name, price);
    }
    
}

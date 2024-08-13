package tpo.uade.api.model;
import lombok.Data;

@Data
public class ProductModel {
    private int productId;
    private String name;
    private double price;

    public ProductModel (int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

}

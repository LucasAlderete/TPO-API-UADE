package tpo.uade.api.model;
import lombok.Data;

@Data
public class ProductModel {
    private int productId;
    private String name;
    private double price;
    private String category;
    private int stock;
    private String description;

    public ProductModel (int productId, String name, double price, String category, int stock, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.description = description;
    }
}

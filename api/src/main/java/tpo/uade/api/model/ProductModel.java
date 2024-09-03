package tpo.uade.api.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue
    @Column(name= "product_id",unique = true, nullable = false)
    private long productId;
    @Column(name= "product_name",nullable = false)
    private String name;
    @Column(name= "product_price", nullable = false)
    private double price;
    @Column(name= "product_category", nullable = false)
    private String category;
    @Column(name= "product_stock", nullable = false)
    private int stock;
    @Column(name= "product_description", nullable = false)
    private String description;


}

package tpo.uade.api.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
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
     @Column(name = "additional_information", nullable = false)
    private String additionalInformation;
    @Column(name = "product_images", nullable = false)
    private List<String> urlImage;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryModel category;
    @ManyToMany(mappedBy = "favoriteProducts")
    private List<UserModel> usersWhoFavorited;
 
}

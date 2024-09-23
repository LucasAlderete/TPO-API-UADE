package tpo.uade.api.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
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

    @Column(name = "secure_id", unique = true, nullable = false, updatable = false)
    private String secureId;

    @Column(name= "product_name",nullable = false)
    private String name;

    @Column(name= "product_price", nullable = false)
    private double price;

    @Column(name= "product_stock", nullable = false)
    private int stock;

    @Column(name= "product_description", nullable = false)
    private String description;

     @Column(name = "additional_information", nullable = false)
    private String additionalInformation;

    @OneToMany
    @JoinColumn(name="list_images_id")
    private List<ImagesModel> urlImageList;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryModel category;

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<UserModel> usersWhoFavorited;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel owner;

    @PrePersist
    protected void onCreate() {
        this.secureId = UUID.randomUUID().toString();
    }
}

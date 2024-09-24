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
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue
    @Column(name= "id",unique = true, nullable = false)
    private int productId;

    @Column(name = "secure_id", unique = true, nullable = false, updatable = false)
    private String secureId;

    @Column(name= "name",nullable = false)
    private String name;

    @Column(name= "price", nullable = false)
    private double price;

    @Column(name= "stock", nullable = false)
    private int stock;

    @Column(name= "description", nullable = false)
    private String description;

     @Column(name = "additional_information", nullable = false)
    private String additionalInformation;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImagesModel> urlImageList;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryModel category;

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<UserModel> usersWhoFavorited;

    @PrePersist
    protected void onCreate() {
        this.secureId = UUID.randomUUID().toString();
    }

}

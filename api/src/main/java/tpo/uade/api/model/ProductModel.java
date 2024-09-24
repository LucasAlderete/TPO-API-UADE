package tpo.uade.api.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductModel {
    @Id
    @GeneratedValue
    @Column(name= "id",unique = true, nullable = false)
    private int id;

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

     @Column(name = "additional_information")
    private String additionalInformation;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImagesModel> urlImageList;

    @Column(name = "highlighted", nullable = false)
    private boolean highlighted;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryModel category;

    @Column(name = "url_image")
    private String urlImage;

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<UserModel> usersWhoFavorited;

    @PrePersist
    protected void onCreate() {
        this.secureId = "SI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}

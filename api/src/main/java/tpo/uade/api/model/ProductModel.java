package tpo.uade.api.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name= "stock", nullable = false)
    private int stock;

    @Column(name = "additional_information")
    private String additionalInformation;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImagesModel> urlImageList;

    @Column(name = "highlighted", nullable = false)
    private boolean highlighted;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryModel category;

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<UserModel> usersWhoFavorited;

    @Column(name = "secure_id", unique = true, nullable = false, updatable = false)
    private String secureId;

    @PrePersist
    protected void onCreate() {
        this.secureId = "SI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
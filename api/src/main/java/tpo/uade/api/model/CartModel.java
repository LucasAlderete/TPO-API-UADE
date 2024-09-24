package tpo.uade.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "carts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CartModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ItemModel> items;

    @Column(name = "total")
    private Double total = 0.0;
}

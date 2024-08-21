package tpo.uade.api.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, precision=12, scale=2)
    private BigDecimal price;
}

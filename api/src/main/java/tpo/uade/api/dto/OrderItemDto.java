package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto implements Serializable {

    @NotBlank(message = "product must not be null nor empty")
    private String product; //TODO: no coincide con el entity

    @NotBlank(message = "quantity must not be null nor empty")
    private Integer quantity;

    @NotBlank(message = "price must not be null nor empty")
    private Integer price;

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
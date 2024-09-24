package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDto implements Serializable {
    private List<ItemDto> items;

    @NotBlank(message = "total must not be null nor empty")
    private Double total;
}

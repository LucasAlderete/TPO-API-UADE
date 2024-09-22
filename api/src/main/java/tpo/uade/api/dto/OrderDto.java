package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
    @NotBlank(message = "id must not be null nor empty")
    private Long id;

    @NotBlank(message = "date must not be null nor empty")
    private LocalDateTime date;

    @NotBlank(message = "items must not be null nor empty")
    private List<OrderItemDto> items;

    @NotBlank(message = "total must not be null nor empty")
    private Double total;
}

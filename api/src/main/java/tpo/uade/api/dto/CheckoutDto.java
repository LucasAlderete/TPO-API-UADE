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
public class CheckoutDto implements Serializable {

    @NotBlank(message = "checkout state must not be null nor empty")
    private Boolean success;

    private List<String> products;
}

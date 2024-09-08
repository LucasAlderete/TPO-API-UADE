package tpo.uade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavigationDto implements Serializable {
    private int productId;
    private int userId;
    private LocalDateTime viewedAt;
}

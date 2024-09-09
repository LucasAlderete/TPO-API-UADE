package tpo.uade.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InfoUserDto {
    private String username;
    private String email;
    private LocalDate birthday;
    private String name;
    private String surname;
}

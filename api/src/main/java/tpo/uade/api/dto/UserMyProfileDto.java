package tpo.uade.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMyProfileDto {
    private String username;
    private String email;
    private LocalDate birthday;
    private String name;
    private String surname;
}

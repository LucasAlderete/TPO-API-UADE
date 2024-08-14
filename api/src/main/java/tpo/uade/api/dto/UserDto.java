package tpo.uade.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    @NotBlank(message = "username must not be null nor empty")
    private String username;

    @NotBlank(message = "email must not be null nor empty")
    private String email;

    @NotBlank(message = "password must not be null nor empty")
    private String password;

    @NotNull(message = "birthday must not be null nor empty")
    private LocalDate birthday;

    @NotBlank(message = "name must not be null nor empty")
    private String name;

    @NotBlank(message = "surname must not be null nor empty")
    private String surname;

}





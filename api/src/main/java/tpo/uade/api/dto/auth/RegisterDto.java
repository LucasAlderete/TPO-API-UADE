package tpo.uade.api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public record RegisterDto(

        @NotBlank(message = "{register-dto.username-not-blank}")
        String username,

        @NotBlank(message = "{register-dto.email-not-blank}")
        String email,

        @NotBlank(message = "{register-dto.password-not-blank}")
        String password,

        @NotNull(message = "{register-dto.birthday-not-null}")
        LocalDate birthday,

        @NotBlank(message = "{register-dto.name-not-blank}")
        String name,

        @NotBlank(message = "{register-dto.surname-not-blank}")
        String surname

) implements Serializable {}

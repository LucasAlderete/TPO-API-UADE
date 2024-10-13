package tpo.uade.api.dto.auth;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticationRequestDto (

        @NotBlank(message = "{authentication-request-dto.username-not-blank}")
        String username,

        @NotBlank(message = "{authentication-request-dto.password-not-blank}")
        String password

) implements Serializable {}

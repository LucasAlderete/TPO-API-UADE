package tpo.uade.api.dto.auth;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticationRequestDto (
        @NotBlank(message = "{authentication-request-dto.email-not-blank}")
        String email,

        @NotBlank(message = "{authentication-request-dto.password-not-blank}")
        String password
) implements Serializable {}

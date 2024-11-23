package tpo.uade.api.dto.auth;

import java.io.Serializable;

public record AuthenticationResponseDto (

        String token

) implements Serializable {}

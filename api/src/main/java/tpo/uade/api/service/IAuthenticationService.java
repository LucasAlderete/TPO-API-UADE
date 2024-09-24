package tpo.uade.api.service;

import tpo.uade.api.dto.auth.AuthenticationRequestDto;
import tpo.uade.api.dto.auth.AuthenticationResponseDto;
import tpo.uade.api.dto.auth.RegisterDto;

public interface IAuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest);
    AuthenticationResponseDto register(RegisterDto registerRequest);
}

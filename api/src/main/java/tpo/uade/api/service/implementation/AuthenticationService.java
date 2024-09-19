package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.auth.AuthenticationRequestDto;
import tpo.uade.api.dto.auth.AuthenticationResponseDto;
import tpo.uade.api.dto.auth.RegisterDto;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IAuthenticationService;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest) {
        return null;
    }

    @Override
    public AuthenticationResponseDto register(RegisterDto registerRequest) {
        return null;
    }
}

package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tpo.uade.api.config.JwtService;
import tpo.uade.api.dto.auth.AuthenticationRequestDto;
import tpo.uade.api.dto.auth.AuthenticationResponseDto;
import tpo.uade.api.dto.auth.RegisterDto;
import tpo.uade.api.mapper.AuthenticationMapper;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IAuthenticationService;
import tpo.uade.api.service.IUserService;

import java.util.Locale;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final IUserService userService;
    private final AuthenticationMapper authenticationMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

        UserModel userModel = userRepository.findByEmail(authRequest.email())
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return new AuthenticationResponseDto(jwtService.generateToken(userModel));
    }

    @Override
    public AuthenticationResponseDto register(RegisterDto registerRequest) {
        UserModel userModel = userRepository.save(authenticationMapper.mapToDatabaseEntity(registerRequest));

        return new AuthenticationResponseDto(jwtService.generateToken(userModel));
    }
}
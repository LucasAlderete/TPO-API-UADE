package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

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

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationMapper authenticationMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

        UserModel userModel = userRepository.findByUsername(authRequest.username())
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return new AuthenticationResponseDto(jwtService.generateToken(userModel));
    }

    @Override
    public AuthenticationResponseDto register(RegisterDto registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        UserModel userModel = userRepository.save(authenticationMapper.mapToDatabaseEntity(registerRequest));

        return new AuthenticationResponseDto(jwtService.generateToken(userModel));
    }
}
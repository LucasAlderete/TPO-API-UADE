package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.auth.AuthenticationRequestDto;
import tpo.uade.api.dto.auth.AuthenticationResponseDto;
import tpo.uade.api.dto.auth.RegisterDto;
import tpo.uade.api.service.IAuthenticationService;

@Api(value = "Authentication Operations")
@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping(path = "/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> authenticate (@Valid @RequestBody @NotNull(message = "{authentication-controller.authenticate-service.authorization-request-not-null}")
                                                                               AuthenticationRequestDto authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> register (@Valid @RequestBody @NotNull(message = "{authentication-controller.register-service.register-request-not-null}")
                                                                           RegisterDto registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
}
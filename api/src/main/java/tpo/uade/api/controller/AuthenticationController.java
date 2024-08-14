package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.service.implementation.AuthenticationService;

@Api(value = "Authentication Operations")
@RestController
@RequestMapping("/auth")
@Component
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> authenticate (@Valid @RequestBody @NotBlank String credentials) { //TODO -> dev it - probably gonna need a credential object
        return ResponseEntity.ok().build();
    }
}

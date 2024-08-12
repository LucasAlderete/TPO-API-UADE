package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpo.uade.api.service.implementation.AuthenticationService;

@Api(value = "Authentication Operations")
@RestController
@RequestMapping("/auth")
@Component
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> authenticate () { //TODO -> dev it
        return ResponseEntity.ok().build();
    }
}

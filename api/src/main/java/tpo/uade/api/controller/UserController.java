package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.UserDTO;
import tpo.uade.api.service.ICreateUserService;
import tpo.uade.api.service.IGetUserDataService;
import tpo.uade.api.service.implementation.CreateUserService;
import tpo.uade.api.service.implementation.GetUserDataService;

@Api(value = "UserDTO Operations")
@RestController
@RequestMapping("/user")
@Component
public class UserController {

    private final ICreateUserService createUserService;
    private final IGetUserDataService getUserDataService;

    public UserController(CreateUserService createUserService, GetUserDataService getUserDataService) {
        this.createUserService = createUserService;
        this.getUserDataService = getUserDataService;
    }

    //"userDTO object must not be null"
    @PostMapping
    public ResponseEntity<Void> createUser (@NotNull(message = "{}") UserDTO userDTO) { //TODO -> take validation messages to resources.ValidationMessages.properties
        createUserService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserData (String username) {
        return ResponseEntity.ok(getUserDataService.getUserData(username));
    }
}

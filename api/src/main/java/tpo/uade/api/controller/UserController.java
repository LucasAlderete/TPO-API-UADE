package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser (@Valid @RequestBody  @NotNull(message = "{user-controller.create-user-service.user-not-null}") UserDTO userDTO) {
        createUserService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserData (@Valid @NotBlank(message = "{user-controller.get-user-data-service.username-not-blank}") String username) {
        return ResponseEntity.ok(getUserDataService.getUserData(username));
    }
}

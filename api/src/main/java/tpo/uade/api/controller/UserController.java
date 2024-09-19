package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.UserDto;
import tpo.uade.api.service.IUserService;

@Api(value = "User Operations")
@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser (@Valid @RequestBody @NotNull(message = "{user-controller.create-user-service.user-not-null}") UserDto userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserData (@Valid @NotBlank(message = "{user-controller.get-user-data-service.username-not-blank}") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}

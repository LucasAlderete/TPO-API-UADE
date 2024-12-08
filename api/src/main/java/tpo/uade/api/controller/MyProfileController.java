package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.service.IMyProfileService;

@Api(value = "My Profile Operations")
@RestController
@RequestMapping("/my-profile")
@Validated
@RequiredArgsConstructor
public class MyProfileController {

    private final IMyProfileService myProfileService;

    @GetMapping
    public ResponseEntity<UserMyProfileDto> getUserMyProfileDto () {
        return ResponseEntity.ok(myProfileService.getUserWithOrders());
    }

    @PutMapping("/update")
    public ResponseEntity<UserMyProfileDto> setUserMyProfileDto(@RequestBody UserMyProfileDto userToUpdate){
        UserMyProfileDto currentUser = myProfileService.setUser(userToUpdate);
        return ResponseEntity.ok(currentUser);
    }
}

package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.service.IMyProfileService;

@Api(value = "My Profile Operations")
@RestController
@RequestMapping("/my-profile")
@Validated
@AllArgsConstructor
public class MyProfileController {
    private IMyProfileService iMyProfileService;

    @GetMapping
    public ResponseEntity<UserMyProfileDto> getUserMyProfileDto(@RequestHeader(name="Authorization") String token){
        UserMyProfileDto userMyProfileDto = iMyProfileService.getUser(token);
        userMyProfileDto.setOrdersDto(iMyProfileService.getOrders(token));
        return ResponseEntity.ok(userMyProfileDto);
    }
}

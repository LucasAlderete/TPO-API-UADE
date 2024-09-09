package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpo.uade.api.dto.MyProfileDto;
import tpo.uade.api.service.implementation.MyProfileService;

@Api(value = "My Profile Operations")
@RestController
@RequestMapping("/my-profile")
@Validated
@AllArgsConstructor
public class MyProfileController {
    private MyProfileService service;

    @GetMapping("/{id}") //TODO de alguna forma tengo que buscar todo con el id del usuario, por si no es con /id
    public ResponseEntity<MyProfileDto> getMyProfileById(@PathVariable Long id){
        MyProfileDto myProfileDto = service.getMyProfileDtoById(id);
        return ResponseEntity.ok(myProfileDto);
    }
}

package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;

@Api(value = "My Profile Operations")
@RestController
@RequestMapping("/{id}/my-profile")
@Validated
@AllArgsConstructor
public class MyProfileController {
    private IMyProfileService service;

    @GetMapping
    public ResponseEntity<UserMyProfileDto> getUserMyProfileDto(@PathVariable Long id){
        UserMyProfileDto userMyProfileDto = service.getUser(id);
        return ResponseEntity.ok(userMyProfileDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrdersDto(@PathVariable Long id){
        List<OrderDto> ordersDto = service.getOrders(id);
        return ResponseEntity.ok(ordersDto);
    }
}

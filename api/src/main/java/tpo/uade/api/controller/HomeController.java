package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.service.IHomeService;


@Api(value = "Home Operations")
@RestController
@RequestMapping("/home")
@Validated
public class HomeController {

    private final IHomeService homeService;

    public HomeController(IHomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeDto> home() {
        return ResponseEntity.ok(homeService.get());
    }
}

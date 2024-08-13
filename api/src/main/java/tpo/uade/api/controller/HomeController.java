package tpo.uade.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.service.IHomeService;


@RestController
public class HomeController {

    @Autowired
    IHomeService homeService;

    @GetMapping("/home")
    public ResponseEntity<HomeDto> home() {
        return ResponseEntity.ok(homeService.get());
    }
    
}

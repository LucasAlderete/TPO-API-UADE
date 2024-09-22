package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.service.INavigationService;

@Api(value = "Navigation users")
@RestController
@RequestMapping("/navigation")
@Component
@Validated
public class NavigationHistoryController {

    private final INavigationService navigationService;

    public NavigationHistoryController(INavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody @NotNull(message = "{navigation-history-controller.navigation-service.navigation-not-null}") NavigationDto navigationDto) {
        navigationService.save(navigationDto);
        return ResponseEntity.ok().build();
    }
}

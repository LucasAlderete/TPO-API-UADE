package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.INavigationService;
import tpo.uade.api.service.IProductService;

import java.util.List;

@Api(value = "Navigation users")
@RestController
@RequestMapping("/navigation")
@Component
@Validated
@RequiredArgsConstructor
public class NavigationHistoryController {

    private final INavigationService navigationService;
    private final IProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestBody @NotNull(message = "{navigation-history-controller.navigation-service.navigation-not-null}") NavigationDto navigationDto) {
        navigationService.save(navigationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> get() {
        List<NavigationDto> lastFiveVisited = navigationService.getLastFiveVisited();
        List<Long> productsIds = lastFiveVisited.stream().map(NavigationDto::getProductId).toList();
        List<ProductDto> products = productService.getByIds(productsIds);
        return ResponseEntity.ok(products);
    }

}

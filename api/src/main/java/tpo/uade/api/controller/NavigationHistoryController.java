package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Navigation users")
@RestController
@RequestMapping("/navigation")
@Component
@Validated
public class NavigationHistoryController {
}

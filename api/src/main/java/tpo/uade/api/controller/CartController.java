package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.service.ICartService;

@Api(value = "Cart Operations")
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Validated
public class CartController {

    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.addProduct(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProduct(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeProduct(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Void> emptyCart(@RequestParam Long userId) {
        cartService.emptyCart(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutDto> checkout(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.checkout(userId));
    }
}
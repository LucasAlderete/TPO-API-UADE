package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.service.ICartService;

@Api(value = "Cart Operations")
@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Validated
public class CartController {

    private final ICartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
        CartDto cartDetails = cartService.getCart(userId);
        return ResponseEntity.ok(cartDetails);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProductToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Void> emptyCart(@RequestParam Long userId) {
        cartService.emptyCart(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkoutCart(@RequestParam Long userId) {
        cartService.checkoutCart(userId);
        return ResponseEntity.ok().build();
    }
}

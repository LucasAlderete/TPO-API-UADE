package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.service.ICartService;


@Api(value = "Cart Operations")
@RestController
@RequestMapping("/cart")
@Validated
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestParam Long productId) {
        cartService.addProduct(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/decrease_quantity")
    public ResponseEntity<Void> decreaseProductQuantity (@RequestParam Long productId) {
        cartService.decreaseProductQuantity(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProduct(@RequestParam Long productId) {
        cartService.removeProduct(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Void> emptyCart() {
        cartService.emptyCart();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutDto> checkout() {
        return ResponseEntity.ok(cartService.checkout());
    }
}
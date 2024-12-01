package tpo.uade.api.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IFavoriteService;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> add(@PathVariable Long productId) {
        boolean success = favoriteService.add(productId);
        if (success) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId) {
        boolean success = favoriteService.remove(productId);
        if (success) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> get() {
        return ResponseEntity.ok(favoriteService.getByUserLogged());
    }
}

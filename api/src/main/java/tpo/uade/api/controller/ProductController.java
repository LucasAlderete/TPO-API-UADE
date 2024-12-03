package tpo.uade.api.controller;

import io.swagger.annotations.Api;

import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IProductService;

import java.util.List;

@Api(value = "Product Operations")
@RestController
@RequestMapping("/product")
@Component
@Validated
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String secureId) {
        return ResponseEntity.ok(productService.getDtoBySecureId(secureId));

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProductStock(@PathVariable String secureId, @RequestParam @Min(value = 0, message = "The stock must be 0 or greater") int stock) {
        productService.updateStockProduct(secureId, stock);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String secureId) {
        productService.deleteProduct(secureId);
        return ResponseEntity.ok().build();
    }
}
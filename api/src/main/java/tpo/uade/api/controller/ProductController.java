package tpo.uade.api.controller;

import io.swagger.annotations.Api;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.service.IProductService;

import java.util.List;

@Api(value = "Product Operations")
@RestController
@RequestMapping("/product")
@Component
@Validated
//@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProductStock(@PathVariable String id, @RequestParam @Min(value = 0, message = "The stock must be 0 or greater") int stock) {
        try {
            productService.updateStockProduct(id, stock);
            return ResponseEntity.ok("Stock actualizado correctamente.");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{secureId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String secureId) {
        try {
            productService.deleteProduct(secureId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

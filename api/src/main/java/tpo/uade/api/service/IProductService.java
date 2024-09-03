package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface IProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long productId);
    void createProduct(ProductDto product);
    ProductDto updateProduct(Long productId, ProductDto product);
    void deleteProduct(Long productId);
}

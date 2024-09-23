package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface IProductService {
    List<ProductDto> getAll();
    ProductDto getById(long productId);
    void createProduct(ProductDto product);
    ProductDto updateProduct(long productId, ProductDto product);
    void deleteProduct(String productId);
    void updateStockProduct(String productId, int nuevoStock);

}

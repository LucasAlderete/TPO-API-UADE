package tpo.uade.api.service;

import java.util.List;
import java.util.Map;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface IProductService {
    List<ProductDto> getAll();
    ProductDto getById(int productId);
    void createProduct(ProductDto product);
    ProductDto findBySecureId(String secureId);
    void deleteProduct(String productId);
    void updateStockProduct(String productId, int nuevoStock);
    Map<String, List<ProductDto>> getAllByCategory();
    List<ProductDto> getByIds(List<Integer> productsIds);

}

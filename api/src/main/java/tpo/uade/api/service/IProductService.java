package tpo.uade.api.service;

import java.util.List;
import java.util.Map;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface IProductService {
    List<ProductDto> getAll();

    ProductDto getById(Long id);

    void createProduct(ProductDto product);

    void saveProduct(ProductModel product);

    ProductModel getModelBySecureId(String secureId);

    ProductDto getDtoBySecureId(String secureId);

    void deleteProduct(Long id);

    void updateStockProduct(String secureId, int nuevoStock);

    Map<String, List<ProductDto>> getAllByCategory();

    List<ProductDto> getBySecureIds(List<String> secureIds);
    List<ProductDto> getByIds(List<Long> secureIds);

    ProductModel getProductById(Long id);
}
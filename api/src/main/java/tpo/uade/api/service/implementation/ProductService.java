package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.IProductService;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public Map<String, List<ProductDto>> getAllByCategory() {
        return productRepository.findAll().stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.groupingBy(productDto -> productDto.getCategoryName().toLowerCase(Locale.ROOT)));
    }

    /**
     * Obtener todos los productos
     *
     * @return
     */
    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        ProductModel productModel = getProductById(id);
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public ProductModel getModelBySecureId(String secureId) {
        return getProductBySecureId(secureId);
    }

    @Override
    public ProductDto getDtoBySecureId(String secureId) {
        return productMapper.mapFromDatabaseEntity(getModelBySecureId(secureId));
    }

    @Override
    public List<ProductDto> getBySecureIds(List<String> productsIds) {
        return productRepository.findBySecureIdIn(productsIds).stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByIds(List<Long> productsIds) {
        return productRepository.findByIdIn((productsIds)).stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductDto product) {
        productRepository.save(productMapper.mapToDatabaseEntity(product));
    }

    @Override
    public void saveProduct(ProductModel product) {
        productRepository.save(product);
    }

    public void updateStockProduct(String secureId, int nuevoStock) {
        ProductModel productModel = getProductBySecureId(secureId);
        productModel.setStock(nuevoStock);
        productRepository.save(productModel);
    }

    @Override
    public void deleteProduct(String secureId) {
        productRepository.deleteBySecureId(secureId);
    }

    private ProductModel getProductBySecureId (String secureId) {
        return productRepository.findBySecureId(secureId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with secure_id: " + secureId));
    }

    private ProductModel getProductById (Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
}
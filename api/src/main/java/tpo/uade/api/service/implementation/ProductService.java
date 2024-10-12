package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }


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
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("product doesn't exist"));
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public ProductDto findBySecureId(Long secureId) {
        ProductModel productModel = productRepository.findBySecureId(secureId)
                .orElseThrow(() -> new NoSuchElementException("product doesn't exist"));
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public List<ProductDto> getByIds(List<Long> productsIds) {
        return productRepository.findByIdIn(productsIds).stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct(ProductDto product) {
        productRepository.save(productMapper.mapToDatabaseEntity(product));
    }

    public void updateStockProduct(Long secureId, int nuevoStock) {
        ProductModel productModel = productRepository.findBySecureId(secureId)
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + secureId + " doesn't exist"));
        productModel.setStock(nuevoStock);
        productRepository.save(productModel);
    }

    @Override
    public void deleteProduct(Long secureId) {
        ProductModel product = productRepository.findBySecureId(secureId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with secure_id: " + secureId));
        productRepository.delete(product);
    }
}

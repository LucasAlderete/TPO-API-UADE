package tpo.uade.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    /**
     * Obtener todos los productos
     *
     * @return
     */
    @Override
    public List<ProductDto> getAll() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = productModelList.stream()
                .map(product -> productMapper.mapFromDatabaseEntity(product))
                .collect(Collectors.toList());

        return productDtoList;
    }

    @Override
    public ProductDto getById(Long id) {
        ProductModel productModel = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public void createProduct(ProductDto product) {
        productRepository.save(productMapper.mapToDatabaseEntity(product));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto product) {
        Optional<ProductModel> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            ProductModel oldProduct = existingProduct.get();

            oldProduct.setName(product.getName());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setStock(product.getStock());
            oldProduct.setPrice(product.getPrice());

            productRepository.save(oldProduct);
            return productMapper.mapFromDatabaseEntity(oldProduct);
        } else {
            throw new RuntimeException("Product with ID " + id + " not found");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
        productRepository.deleteById(productId);
    }
}

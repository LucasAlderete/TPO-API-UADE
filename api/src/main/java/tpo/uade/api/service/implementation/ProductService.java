package tpo.uade.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.NoSuchElementException;
import java.util.Optional;
=======
import java.util.Locale;
import java.util.Map;
>>>>>>> main
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository, UserRepository userRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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
    public ProductDto getById(int id) {
        ProductModel productModel = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public ProductDto findBySecureId(String secureId) {
        ProductModel productModel = productRepository.findBySecureId(secureId).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));
        return productMapper.mapFromDatabaseEntity(productModel);
    }

    @Override
    public List<ProductDto> getByIds(List<Integer> productsIds) {
        return productRepository.findByIdIn(productsIds).stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void createProduct(ProductDto product) {
        productRepository.save(productMapper.mapToDatabaseEntity(product));
    }

    public void updateStockProduct(String secureId, int nuevoStock) {
        ProductModel productModel = productRepository.findBySecureId(secureId).orElseThrow(() -> new NoSuchElementException("Product with ID " + secureId + " doesn't exist"));

        productModel.setStock(nuevoStock);
        productRepository.save(productModel);
    }

    @Override
    public void deleteProduct(String secureId) {
        productRepository.deleteBySecureId(secureId).orElseThrow(() -> new NoSuchElementException("Product with ID " + secureId + " doesn't exist"));
    }
}

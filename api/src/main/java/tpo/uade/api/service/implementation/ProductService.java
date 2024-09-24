package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Map<String, List<ProductDto>> getAllByCategory() {
        return productRepository.findAll().stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.groupingBy(productDto -> productDto.getCategoryName().toLowerCase(Locale.ROOT)));
    }

    /**
     * Obtener todos los productos
     * @return
     */
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

    /**
     * Obtener producto por id.
     * @param productId
     * @return
     */
    public ProductDto getById(int productId) {
        return null;
    }

    @Override
    public List<ProductDto> getByIds(List<Integer> productsIds) {
        return productRepository.findByIdIn(productsIds).stream()
                .map(productMapper::mapFromDatabaseEntity)
                .collect(Collectors.toList());
    }

}

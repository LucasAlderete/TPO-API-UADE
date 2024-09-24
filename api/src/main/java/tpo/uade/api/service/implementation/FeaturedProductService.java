package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IFeaturedProductService;
import tpo.uade.api.service.IProductService;

@Service
public class FeaturedProductService implements IFeaturedProductService {
    private final int LIMIT = 8;
    private final IProductService productService;

    FeaturedProductService(IProductService productService)  {
        this.productService = productService;
    }
    /**
     * Obtiene productos destacados
     * @return
     */
    public List<ProductDto> getAll() {
        return productService.getAll().stream()
                .filter(ProductDto::isHighlighted)
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    public ProductDto getById(int productId) {
        return null;
    }

    public void save() {

    }

    public void update() {

    }

    public void delete() {

    }

}

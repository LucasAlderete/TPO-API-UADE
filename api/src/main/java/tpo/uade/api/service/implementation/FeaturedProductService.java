package tpo.uade.api.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IFeaturedProductService;

@Service
public class FeaturedProductService implements IFeaturedProductService {
    
    /**
     * Obtiene productos destacados
     * @return
     */
    public List<ProductDto> getAll() {
        return List.of();
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

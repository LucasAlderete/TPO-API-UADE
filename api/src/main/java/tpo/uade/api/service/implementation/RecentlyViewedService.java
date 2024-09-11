package tpo.uade.api.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IRecentlyViewedService;

@Service
public class RecentlyViewedService implements IRecentlyViewedService {

    /**
     * Obtener todos los productos vistos recientemente por usuario
     * @param i 
     * @param limit representa la cantidad maxima de productos a mostrar
     * @return
     */
    public List<ProductDto> getAllByUser(int userId, int limit) {
        return List.of();
    }

    /**
     * Guarda producto visto por el usuario
     * @param userId
     * @param productId
     */
    public void save(String userId, int productId) {

    }
    
}

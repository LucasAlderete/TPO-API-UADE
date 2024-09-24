package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.NavigationMapper;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.repository.NavigationRespository;
import tpo.uade.api.service.IRecentlyViewedService;

@Service
public class RecentlyViewedService implements IRecentlyViewedService {

    private final NavigationRespository navigationRespository;
    private final NavigationMapper navigationMapper;
    private final ProductMapper productMapper;
    private final ProductService productService;

    RecentlyViewedService(NavigationRespository navigationRespository, NavigationMapper navigationMapper, ProductMapper productMapper, ProductService productService) {
        this.navigationRespository = navigationRespository;
        this.navigationMapper = navigationMapper;
        this.productMapper = productMapper;
        this.productService = productService;
    }

    /**
     * Obtener todos los productos vistos recientemente por usuario
     * @param i 
     * @param limit representa la cantidad maxima de productos a mostrar
     * @return
     */
    public List<ProductDto> getAllByUser(Long userId, int limit) {
        List<NavigationModel> navigationModels = navigationRespository.findByUser_UserId(userId);

        if (navigationModels == null || navigationModels.isEmpty()) {
            return List.of();
        }

        return navigationModels.stream()
                .map(navigationModel -> productMapper.mapFromDatabaseEntity(navigationModel.getProduct()))
                .limit(limit)
                .collect(Collectors.toList());
    }
    
}

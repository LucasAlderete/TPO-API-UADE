package tpo.uade.api.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.NavigationMapper;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.repository.NavigationRespository;
import tpo.uade.api.service.INavigationService;
import tpo.uade.api.service.IRecentlyViewedService;

@RequiredArgsConstructor
@Service
public class RecentlyViewedService implements IRecentlyViewedService {

    private final INavigationService navigationService;
    private final ProductService productService;

    public List<ProductDto> get() {
        List<NavigationDto> lastFiveVisited = navigationService.getLastFiveVisited();
        List<Long> productsIds = lastFiveVisited.stream().map(NavigationDto::getProductId).toList();
        return productService.getByIds(productsIds);
    }
    
}

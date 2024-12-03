package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.service.IFeaturedProductService;
import tpo.uade.api.service.IHomeService;
import tpo.uade.api.service.IProductService;
import tpo.uade.api.service.IRecentlyViewedService;
import tpo.uade.api.service.IRecommendationService;

@RequiredArgsConstructor
@Service
public class HomeService implements IHomeService {
    
    private final IProductService productService;
    private final IFeaturedProductService featuredProductService;
    private final IRecentlyViewedService recentlyViewedService;

    public HomeDto get() {
        HomeDto homeDto = new HomeDto();
        homeDto.setFeaturedProducts(featuredProductService.getAll());
        homeDto.setProducts(productService.getAllByCategory());
        homeDto.setRecentlyViewedProducts(recentlyViewedService.get());
        return homeDto;
    }

}

package tpo.uade.api.service.implementation;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.service.IFeaturedProductService;
import tpo.uade.api.service.IHomeService;
import tpo.uade.api.service.IProductService;
import tpo.uade.api.service.IRecentlyViewedService;
import tpo.uade.api.service.IRecommendationService;

@Service
public class HomeService implements IHomeService {
    
    private final IProductService productService;
    private final IFeaturedProductService featuredProductService;
    private final IRecentlyViewedService recentlyViewedService;
    private final IRecommendationService recommendationService;

    public HomeService(IProductService productService, IFeaturedProductService featuredProductService, IRecentlyViewedService recentlyViewedService, IRecommendationService recommendationService) {
        this.productService = productService;
        this.featuredProductService = featuredProductService;
        this.recentlyViewedService = recentlyViewedService;
        this.recommendationService = recommendationService;
    }

    public HomeDto get() {
        HomeDto homeDto = new HomeDto();
        homeDto.setFeaturedProducts(featuredProductService.getAll());
        homeDto.setProducts(productService.getAllByCategory());
        homeDto.setRecentlyViewedProducts(recentlyViewedService.getAllByUser(1L, 10));
        return homeDto;
    }

}

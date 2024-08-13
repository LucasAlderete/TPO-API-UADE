package tpo.uade.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpo.uade.api.dto.HomeDto;

@Service
public class HomeService implements IHomeService {
    
    @Autowired
    IProductService productService;
    
    @Autowired
    IFeaturedProductService featuredProductService;

    @Autowired
    IRecentlyViewedService recentlyViewedService;


    public HomeDto get() {
        HomeDto homeDto = new HomeDto();
        homeDto.setFeaturedProducts(featuredProductService.getAll());
        homeDto.setProducts(productService.getAll());
        homeDto.setRecentlyViewedProducts(recentlyViewedService.getAllByUser(1, 10));

        return homeDto;
    }
}

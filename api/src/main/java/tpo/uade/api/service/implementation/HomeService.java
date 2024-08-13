package tpo.uade.api.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.service.IFeaturedProductService;
import tpo.uade.api.service.IHomeService;
import tpo.uade.api.service.IProductService;
import tpo.uade.api.service.IRecentlyViewedService;

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

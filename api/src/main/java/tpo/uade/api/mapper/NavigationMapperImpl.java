package tpo.uade.api.mapper;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.model.ProductModel;

@Service
public class NavigationMapperImpl implements NavigationMapper{
    @Override
    public NavigationDto mapFromDatabaseEntity(NavigationModel navigationModel) {
        return new NavigationDto(navigationModel.getProduct().getId(),
                navigationModel.getViewedAt());
    }

    @Override
    public NavigationModel mapToDatabaseEntity(NavigationDto navigationDto) {
        NavigationModel navigationModel = new NavigationModel();

        ProductModel product = new ProductModel();
        product.setId(navigationDto.getProductId());
        navigationModel.setProduct(product);

        return navigationModel;
    }
}

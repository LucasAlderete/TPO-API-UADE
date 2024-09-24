package tpo.uade.api.mapper;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.NavigationDto;
import tpo.uade.api.model.NavigationModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.UserModel;

@Service
public class NavigationMapperImpl implements NavigationMapper{
    @Override
    public NavigationDto mapFromDatabaseEntity(NavigationModel navigationModel) {
        return new NavigationDto(navigationModel.getProduct().getId(),
                navigationModel.getUser().getUserId(),
                navigationModel.getViewedAt());
    }

    @Override
    public NavigationModel mapToDatabaseEntity(NavigationDto navigationDto) {
        NavigationModel navigationModel = new NavigationModel();

        UserModel user = new UserModel();
        user.setUserId(navigationDto.getUserId());
        navigationModel.setUser(user);

        ProductModel product = new ProductModel();
        product.setId(navigationDto.getProductId());
        navigationModel.setProduct(product);

        return navigationModel;
    }
}

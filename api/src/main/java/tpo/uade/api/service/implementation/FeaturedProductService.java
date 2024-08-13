package tpo.uade.api.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import tpo.uade.api.model.FeaturedProductModel;
import tpo.uade.api.service.IFeaturedProductService;
import tpo.uade.api.service.MockProductHelper;

@Service
public class FeaturedProductService implements IFeaturedProductService {
    
    /**
     * Obtiene productos destacados
     * @return
     */
    public List<FeaturedProductModel> getAll() {
        return MockProductHelper.getListFeaturedProduct();
    }

    public FeaturedProductModel getById(int productId) {
        return MockProductHelper.getFeaturedProduct();
    }

    public void save() {

    }

    public void update() {

    }

    public void delete() {

    }

}

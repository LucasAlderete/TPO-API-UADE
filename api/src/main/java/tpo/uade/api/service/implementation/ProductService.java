package tpo.uade.api.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import tpo.uade.api.model.ProductModel;
import tpo.uade.api.service.IProductService;
import tpo.uade.api.service.MockProductHelper;

@Service
public class ProductService implements IProductService {
    
    /**
     * Obtener todos los productos
     * @return
     */
    public List<ProductModel> getAll() {
        return MockProductHelper.getList();
    }

    /**
     * Obtener producto por id.
     * @param productId
     * @return
     */
    public ProductModel getById(int productId) {
        return MockProductHelper.get();
    }


}

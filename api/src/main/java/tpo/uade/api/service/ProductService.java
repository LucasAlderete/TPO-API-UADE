package tpo.uade.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tpo.uade.api.model.ProductModel;

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

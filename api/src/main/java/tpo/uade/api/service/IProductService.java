package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.model.ProductModel;

public interface IProductService {
    public List<ProductModel> getAll();
    public ProductModel getById(int productId);
}

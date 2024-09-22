package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;

public interface IFeaturedProductService {
    List<ProductDto> getAll();
    ProductDto getById(int productId);
    void save();
    void update();
    void delete();
}

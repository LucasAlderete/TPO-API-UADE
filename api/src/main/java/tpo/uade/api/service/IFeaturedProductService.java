package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;

public interface IFeaturedProductService {
    public List<ProductDto> getAll();
    public ProductDto getById(int productId);
    public void save();
    public void update();
    public void delete();
}

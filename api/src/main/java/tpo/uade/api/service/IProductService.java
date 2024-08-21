package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;

public interface IProductService {
    public List<ProductDto> getAll();
    public ProductDto getById(int productId);
}

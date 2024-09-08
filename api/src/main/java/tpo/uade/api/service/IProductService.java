package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;

public interface IProductService {
    List<ProductDto> getAll();
    ProductDto getById(int productId);
}

package tpo.uade.api.service;

import java.util.List;
import java.util.Map;

import tpo.uade.api.dto.ProductDto;

public interface IProductService {
    List<ProductDto> getAll();
    ProductDto getById(int productId);
    Map<String, List<ProductDto>> getAllByCategory();
    List<ProductDto> getByIds(List<Integer> productsIds);

}

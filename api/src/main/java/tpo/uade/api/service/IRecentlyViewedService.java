package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.dto.ProductDto;

public interface IRecentlyViewedService {
    public List<ProductDto> getAllByUser(int userId, int limit);
    public void save(String userId, int productId);
}

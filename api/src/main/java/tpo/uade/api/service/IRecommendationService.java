package tpo.uade.api.service;

import tpo.uade.api.dto.ProductDto;

import java.util.List;

public interface IRecommendationService {
    List<ProductDto> getAll();
}

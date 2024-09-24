package tpo.uade.api.service.implementation;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.IRecommendationService;

import java.util.List;

@Service
public class RecommendationService implements IRecommendationService {
    public List<ProductDto> getAll() {
        return List.of();
    }
}

package tpo.uade.api.service;

import tpo.uade.api.dto.ProductDto;

import java.util.List;

public interface IFavoriteService {
    boolean add(Long productId);
    boolean remove(Long productId);
    List<ProductDto> getByUserLogged();
}

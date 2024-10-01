package tpo.uade.api.service;

import tpo.uade.api.dto.ProductDto;

import java.util.List;

public interface IFavoriteService {
    boolean add(int productId, long user_id);
    boolean remove(int productId, long user_id);
}

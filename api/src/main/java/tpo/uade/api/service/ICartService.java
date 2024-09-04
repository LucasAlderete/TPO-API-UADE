package tpo.uade.api.service;

import tpo.uade.api.dto.CartDto;

public interface ICartService {
    void emptyCart(Long userId);

    void addProduct(Long userId, Long productId);

    void removeProduct(Long userId, Long productId);

    CartDto getCart(Long userId);

    boolean checkout(Long userId);
}

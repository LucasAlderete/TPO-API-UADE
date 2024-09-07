package tpo.uade.api.service;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;

public interface ICartService {
    void emptyCart(Long userId);

    void addProduct(Long userId, Long productId);

    void removeProduct(Long userId, Long productId);

    CartDto getCart(Long userId);

    CheckoutDto checkout(Long userId);
}

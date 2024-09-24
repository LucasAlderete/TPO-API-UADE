package tpo.uade.api.service;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;

public interface ICartService {
    void emptyCart(Long userId);

    void addProduct(Long userId, Integer productId);

    void removeProduct(Long userId, Integer productId);

    CartDto getCart(Long userId);

    CheckoutDto checkout(Long userId);
}

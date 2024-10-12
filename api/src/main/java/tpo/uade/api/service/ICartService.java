package tpo.uade.api.service;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;

public interface ICartService {
    void emptyCart();

    void addProduct(Long productId);

    void removeProduct(Long productId);

    CartDto getCart();

    CheckoutDto checkout();
}

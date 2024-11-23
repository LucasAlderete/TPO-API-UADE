package tpo.uade.api.service;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;

public interface ICartService {
    void emptyCart();

    void addProduct(String productSecureId);

    void removeProduct(String productSecureId);

    CartDto getCart();

    CheckoutDto checkout();
}
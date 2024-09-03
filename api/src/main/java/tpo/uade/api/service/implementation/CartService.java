package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.UserDto;
import tpo.uade.api.mapper.CartMapper;
import tpo.uade.api.model.CartModel;
import tpo.uade.api.model.ItemModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.repository.CartRepository;
import tpo.uade.api.repository.ItemRepository;
import tpo.uade.api.service.ICartService;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartDto getCart (Long userId) throws NoSuchElementException {
        CartModel cartModel = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
        CartDto cartDto = cartMapper.toDto(cartModel);
        return cartDto;
    }

    public void addProduct (Long userId, Long productId) throws NoSuchElementException {
        CartModel cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
        Long cartId = cart.getId();

        Optional<ItemModel> item = itemRepository.findByCartIdAndProductId(cartId, productId);

        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

        if (item.isPresent()) {
            ItemModel itemModel = item.get();
            itemModel.setQuantity(itemModel.getQuantity() + 1);
            itemModel.setPrice(itemModel.getQuantity() * product.getPrice());

            cart.setTotal(cart.getTotal() + product.getPrice());
            itemRepository.save(itemModel);
            cartRepository.save(cart);
        } else {
            ItemModel itemModel = new ItemModel();
            itemModel.setCart(cart);
            itemModel.setProduct(product);
            itemModel.setQuantity(1);
            itemModel.setPrice(product.getPrice());

            if (cart.getItems() == null) {
                cart.setItems(new ArrayList<>());
            }

            cart.getItems().add(itemModel);
            cart.setTotal(cart.getTotal() + product.getPrice());

            itemRepository.save(itemModel);
            cartRepository.save(cart);
        }
    };

    public void removeProduct (Long userId, Long productId) throws NoSuchElementException {
        CartModel cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
        Long cartId = cart.getId();

        ItemModel itemModel = itemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new NoSuchElementException("item doesn't exist"));

        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

        if (itemModel.getQuantity() > 1) {
            itemModel.setQuantity(itemModel.getQuantity() - 1);
            itemRepository.save(itemModel);
        } else {
            itemRepository.delete(itemModel);
        }


        cart.setTotal(cart.getTotal() - product.getPrice());
    };

    public void emptyCart (Long userId) throws NoSuchElementException {
        CartModel cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));

        cart.setItems(null);
        cart.setTotal(0.0);

        itemRepository.deleteAll(cart.getItems());
        cartRepository.save(cart);
    }

    public void checkout (Long userId) throws NoSuchElementException {

    }
}

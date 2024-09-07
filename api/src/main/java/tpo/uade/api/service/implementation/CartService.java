package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.mapper.CartMapper;
import tpo.uade.api.mapper.ItemMapper;
import tpo.uade.api.model.*;
import tpo.uade.api.repository.CartRepository;
import tpo.uade.api.repository.ItemRepository;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.service.ICartService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

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
    }

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
    }

    public void emptyCart (Long userId) throws NoSuchElementException {
        CartModel cart = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));

        cart.setItems(null);
        cart.setTotal(0.0);

        itemRepository.deleteAll(cart.getItems());
        cartRepository.save(cart);
    }

    @Transactional(rollbackFor = Exception.class)
    public CheckoutDto checkout (Long userId) throws NoSuchElementException {
        CartModel cartModel = cartRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
        CheckoutDto response = new CheckoutDto();
        List<String> outOfStockProducts = new ArrayList<>();

        for (ItemModel item : cartModel.getItems()) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                outOfStockProducts.add(item.getProduct().getName());
            }
        }

        if (!outOfStockProducts.isEmpty()) {
            response.setSuccess(false);
            response.setProducts(outOfStockProducts);
            return response;
        }

        cartModel.getItems().forEach(item -> {
            ProductModel productModel = item.getProduct();
            productModel.setStock(productModel.getStock() - item.getQuantity());
            productRepository.save(productModel);
        });

        OrderModel orderModel = cartMapper.mapCartToOrder(cartModel);

        List<OrderItemModel> orderItems = cartModel.getItems()
                .stream()
                .map(itemMapper::mapItemToOrderItem)
                .collect(Collectors.toList());


        orderItems.forEach(item -> item.setOrder(orderModel));
        orderModel.setItems(orderItems);

        orderRepository.save(orderModel);

        response.setSuccess(true);

        return response;
    }
}

package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.mapper.CartMapper;
import tpo.uade.api.mapper.ItemMapper;
import tpo.uade.api.model.*;
import tpo.uade.api.repository.*;
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
    private final UserService userService;
    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    public CartDto getCart (Long userId) throws NoSuchElementException {
        return cartMapper.toDto(getCartByUserId(userId));
    }

    public void addProduct (Long userId, Long productId) throws NoSuchElementException { //TODO -> secure o db id?
        UserModel user = userService.getUserModelByUsername();

        CartModel cart = cartRepository.findByUser_UserId(userId).orElseGet(() -> {
            CartModel newCart = new CartModel();
            newCart.setUser(user);
            newCart.setTotal(0.0);
            return cartRepository.save(newCart);
        });

        Optional<ItemModel> item = itemRepository.findByCartIdAndProductId(cart.getId(), productId);

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

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
        CartModel cart = getCartByUserId(userId);
        Long cartId = cart.getId();

        ItemModel itemModel = itemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new NoSuchElementException("item doesn't exist"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

        if (itemModel.getQuantity() > 1) {
            itemModel.setQuantity(itemModel.getQuantity() - 1);
            itemModel.setPrice(itemModel.getQuantity() * product.getPrice());
            itemRepository.save(itemModel);
        } else {
            itemRepository.delete(itemModel);
        }


        cart.setTotal(cart.getTotal() - product.getPrice());

        cartRepository.save(cart);
    }

    @Transactional
    public void emptyCart (Long userId) throws NoSuchElementException {
        CartModel cart = getCartByUserId(userId);

        itemRepository.deleteAll(cart.getItems());
        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);

        cartRepository.save(cart);
    }

    @Transactional(rollbackFor = Exception.class)
    public CheckoutDto checkout (Long userId) throws NoSuchElementException {
        CartModel cartModel = getCartByUserId(userId);
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
        orderModel.setId(null);
        orderModel.setDate(java.time.LocalDateTime.now());


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

    private CartModel getCartByUserId (Long userId) {
        return cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
    }
}

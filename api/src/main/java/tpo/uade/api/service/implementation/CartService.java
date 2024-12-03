package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.mapper.CartMapper;
import tpo.uade.api.mapper.ItemMapper;
import tpo.uade.api.model.CartModel;
import tpo.uade.api.model.ItemModel;
import tpo.uade.api.model.OrderItemModel;
import tpo.uade.api.model.OrderModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.CartRepository;
import tpo.uade.api.repository.ItemRepository;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.ICartService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final ProductService productService;
    private final UserService userService;

    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    @Transactional(rollbackFor = Exception.class)
    public CartDto getCart () throws NoSuchElementException {
        if (cartRepository.findByUser_UserId(userService.getUserIdByUsername()).isPresent()) {
            return cartMapper.toDto(getCartByUserId(userService.getUserIdByUsername()));
        } else {
            UserModel user = userService.getUserModelByUsername();
            List<ItemModel> items = new ArrayList<>();
            Double total = 0.0;

            CartModel cartModel = new CartModel();
            cartModel.setUser(user);
            cartModel.setItems(items);
            cartModel.setTotal(total);

            cartRepository.save(cartModel);

            return cartMapper.toDto(cartModel);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void addProduct (Long productId) throws NoSuchElementException {
        UserModel user = userService.getUserModelByUsername();

        CartModel cart = getCartByUserId(user.getUserId());

        ProductModel product = productService.getProductById(productId);
        Optional<ItemModel> item = itemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

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

            cart.getItems().add(itemModel);
            cart.setTotal(cart.getTotal() + product.getPrice());

            itemRepository.save(itemModel);
            cartRepository.save(cart);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void decreaseProductQuantity (Long productId) throws NoSuchElementException {
        CartModel cart = getCartByUserId(userService.getUserIdByUsername());
        Long cartId = cart.getId();

        ItemModel itemModel = itemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new NoSuchElementException("item doesn't exist"));

        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

        if (itemModel.getQuantity() > 1) {
            itemModel.setQuantity(itemModel.getQuantity() - 1);
            itemModel.setPrice(itemModel.getQuantity() * product.getPrice());

            cart.setTotal(cart.getTotal() - product.getPrice());

            itemRepository.save(itemModel);
            cartRepository.save(cart);
        } else {
            removeProduct(productId);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void removeProduct (Long productId) throws NoSuchElementException {
        CartModel cart = getCartByUserId(userService.getUserIdByUsername());
        Long cartId = cart.getId();

        ItemModel itemModel = itemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() -> new NoSuchElementException("item doesn't exist"));
        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product doesn't exist"));

        cart.setTotal(cart.getTotal() - product.getPrice() * itemModel.getQuantity());
        cart.setItems(cart.getItems().stream()
                .filter(item -> !item.getProduct().getId().equals(productId))
                .collect(Collectors.toList()));

        cartRepository.save(cart);
        itemRepository.delete(itemModel);
    }


    @Transactional(rollbackFor = Exception.class)
    public void emptyCart () throws NoSuchElementException {
        CartModel cart = getCartByUserId(userService.getUserIdByUsername());

        itemRepository.deleteAll(cart.getItems());
        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);

        cartRepository.save(cart);
    }

    @Transactional(rollbackFor = Exception.class)
    public CheckoutDto checkout () throws NoSuchElementException {
        CartModel cartModel = getCartByUserId(userService.getUserIdByUsername());
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
            productService.saveProduct(productModel);
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

        emptyCart();

        response.setSuccess(true);

        return response;
    }

    private CartModel getCartByUserId (Long userId) {
        return cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new NoSuchElementException("cart doesn't exist"));
    }

    private ItemModel getItemByCartIdAndProductId (Long cartId, Long productId) {
        return itemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new NoSuchElementException("item doesn't exist"));
    }
}
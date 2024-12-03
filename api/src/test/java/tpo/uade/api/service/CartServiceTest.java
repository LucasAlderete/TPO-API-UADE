package tpo.uade.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import tpo.uade.api.dto.CartDto;
import tpo.uade.api.dto.CheckoutDto;
import tpo.uade.api.mapper.CartMapper;
import tpo.uade.api.mapper.ItemMapper;
import tpo.uade.api.model.*;
import tpo.uade.api.repository.CartRepository;
import tpo.uade.api.repository.ItemRepository;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.implementation.CartService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCart_Success() {
        // Arrange
        Long userId = 1L;
        CartModel cartModel = new CartModel();
        cartModel.setId(1L);

        CartDto cartDto = new CartDto(); // Crear el CartDto simulado
        when(cartRepository.findByUser_UserId(userId)).thenReturn(Optional.of(cartModel));
        when(cartMapper.toDto(cartModel)).thenReturn(cartDto); // Simula el mapeo correctamente

        // Act
        CartDto result = cartService.getCart();

        // Assert
        assertNotNull(result, "Expected CartDto to be not null but was null.");
        verify(cartRepository, times(1)).findByUser_UserId(userId);
    }


    @Test
    public void testAddProduct_ItemExists() {
        // Arrange
        Long userId = 1L;
        Long productId = 1L;
        CartModel cartModel = new CartModel();
        cartModel.setId(1L);

        ProductModel product = new ProductModel();
        product.setId(productId);
        product.setPrice(100.0);

        ItemModel itemModel = new ItemModel();
        itemModel.setCart(cartModel);
        itemModel.setProduct(product);
        itemModel.setQuantity(1);

        when(cartRepository.findByUser_UserId(userId))
                .thenReturn(Optional.of(cartModel));

        when(itemRepository.findByCartIdAndProductId(cartModel.getId(), product.getId()))
                .thenReturn(Optional.of(itemModel));

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        // Act
        cartService.addProduct(productId);

        // Assert
        assertEquals(2, itemModel.getQuantity());
        verify(itemRepository, times(1)).save(itemModel);
        verify(cartRepository, times(1)).save(cartModel);
    }

    @Test
    public void testCheckout_Success() {
        // Arrange
        Long userId = 1L;
        CartModel cartModel = new CartModel();
        cartModel.setId(1L);

        ProductModel product = new ProductModel();
        product.setStock(5);
        product.setPrice(10.0);

        ItemModel item = new ItemModel();
        item.setProduct(product);
        item.setQuantity(1);

        List<ItemModel> items = new ArrayList<>();
        items.add(item);
        cartModel.setItems(items);

        OrderItemModel orderItem = new OrderItemModel();
        when(cartRepository.findByUser_UserId(userId)).thenReturn(Optional.of(cartModel));
        when(cartMapper.mapCartToOrder(any())).thenReturn(new OrderModel());
        when(itemMapper.mapItemToOrderItem(any(ItemModel.class))).thenReturn(orderItem);

        // Act
        CheckoutDto checkoutDto = cartService.checkout();

        // Assert
        assertNotNull(checkoutDto);
        assertTrue(checkoutDto.getSuccess(), "Expected success to be true but was false.");
        verify(orderRepository, times(1)).save(any(OrderModel.class));
    }

}

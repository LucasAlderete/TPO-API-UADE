package tpo.uade.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.implementation.UserService;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticate_WhenCalled_Success() {
        /*
        // Arrange
        List<ProductDto> featuredProducts = new ArrayList<>();
        Map<String, List<ProductDto>> products = Map.of();
        List<ProductDto> recentlyViewedProducts = new ArrayList<>();

        when(featuredProductService.getAll()).thenReturn(featuredProducts);
        when(productService.getAllByCategory()).thenReturn(products);
        when(recentlyViewedService.getAllByUser(anyLong(), anyInt())).thenReturn(recentlyViewedProducts);

        // Act
        HomeDto result = homeService.get();

        // Assert
        assertNotNull(result);
        assertEquals(featuredProducts, result.getFeaturedProducts());
        assertEquals(products, result.getProducts());
        assertEquals(recentlyViewedProducts, result.getRecentlyViewedProducts());
        verify(featuredProductService).getAll();
        verify(productService).getAllByCategory();
        verify(recentlyViewedService).getAllByUser(1L, 10);

         */
    }

    @Test
    public void authenticate_WhenCalled_Fails() {
        /*
        // Arrange
        List<ProductDto> featuredProducts = new ArrayList<>();
        Map<String, List<ProductDto>> products = Map.of();
        List<ProductDto> recentlyViewedProducts = new ArrayList<>();

        when(featuredProductService.getAll()).thenReturn(featuredProducts);
        when(productService.getAllByCategory()).thenReturn(products);
        when(recentlyViewedService.getAllByUser(anyLong(), anyInt())).thenReturn(recentlyViewedProducts);

        // Act
        HomeDto result = homeService.get();

        // Assert
        assertNotNull(result);
        assertEquals(featuredProducts, result.getFeaturedProducts());
        assertEquals(products, result.getProducts());
        assertEquals(recentlyViewedProducts, result.getRecentlyViewedProducts());
        verify(featuredProductService).getAll();
        verify(productService).getAllByCategory();
        verify(recentlyViewedService).getAllByUser(1L, 10);

         */
    }
}

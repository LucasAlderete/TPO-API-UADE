package tpo.uade.api.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.uade.api.dto.HomeDto;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.service.implementation.HomeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    @Mock
    private IProductService productService;

    @Mock
    private IFeaturedProductService featuredProductService;

    @Mock
    private IRecentlyViewedService recentlyViewedService;

    @Mock
    private IRecommendationService recommendationService;

    @InjectMocks
    private HomeService homeService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void get_WhenCalled_ReturnsHomeDto() {
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
    }
}


package tpo.uade.api.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import tpo.uade.api.config.JwtService;
import tpo.uade.api.dto.auth.RegisterDto;
import tpo.uade.api.model.RoleEnum;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.implementation.AuthenticationService;
import tpo.uade.api.service.implementation.UserService;

@RequiredArgsConstructor
public class AuthenticationServiceTest {

    private UserService userService;
    private AuthenticationService authenticationService;
    private UserRepository userRepository;
    private JwtService jwtService;

    private String token;

    @BeforeAll
    void generalSetUp() {
        token = authenticationService.register(new RegisterDto("prueba", "prueba@prueba.com", "12345",
                null, "nombre", "apellido", RoleEnum.ADMIN)).token();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    void cleanUp() {
        userRepository.deleteById(userRepository.findByUsername(jwtService.extractUsername(token.substring(7).trim())).get().getUserId());
    }

    @Test
    public void authenticate_WhenCalled_Success() {
        /*
        // Arrange
        authenticationService.authenticate()

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

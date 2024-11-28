package tpo.uade.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.uade.api.config.JwtService;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.model.OrderModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.implementation.MyProfileService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyProfileServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserMyProfileMapper userMapper;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private MyProfileService myProfileService;

    private String token;
    private UserModel mockUser;
    private UserMyProfileDto mockUserDto;
    private OrderModel mockOrder;
    private OrderDto mockOrderDto;

    @BeforeEach
    public void setUp() {
        token = "validToken";

        mockUser = new UserModel();
        mockUser.setUserId(1L);
        mockUser.setUsername("user123");

        mockUserDto = new UserMyProfileDto();
        mockUserDto.setUsername("user123");

        mockOrder = new OrderModel();
        mockOrder.setId(1L);

        mockOrderDto = new OrderDto();
        mockOrderDto.setId(1L);
    }

    @Test
    public void getUser_WhenUserExists_ShouldReturnUser() {
        // Arrange
        when(jwtService.extractUsername(token)).thenReturn("user123");
        when(userRepository.findByUsername("user123")).thenReturn(Optional.of(mockUser));
        when(userMapper.toDto(mockUser)).thenReturn(mockUserDto);

        // Act
        UserMyProfileDto result = myProfileService.getUser(token);

        // Assert
        assertNotNull(result);
        assertEquals("user123", result.getUsername());
        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("user123");
        verify(userMapper).toDto(mockUser);
    }

    @Test
    public void getUser_WhenUserDoesNotExist_ShouldThrowException() {
        // Arrange
        when(jwtService.extractUsername(token)).thenReturn("maliciousUser123");
        when(userRepository.findByUsername("maliciousUser123")).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(NoSuchElementException.class, () -> myProfileService.getUser(token));

        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("maliciousUser123");
    }

    @Test
    public void getOrders_WhenUserExists_ShouldReturnOrders() {
        // Arrange
        when(jwtService.extractUsername(token)).thenReturn("user123");
        when(userRepository.findByUsername("user123")).thenReturn(Optional.of(mockUser));
        when(orderRepository.findByUser_UserId(1L)).thenReturn(Optional.of(mockOrder));
        when(orderMapper.toDto(mockOrder)).thenReturn(mockOrderDto);

        // Act
        List<OrderDto> result = myProfileService.getOrders(token);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("user123");
        verify(orderRepository).findByUser_UserId(1L);
        verify(orderMapper).toDto(mockOrder);
    }

    @Test
    public void getOrders_WhenUserDoesNotExist_ShouldThrowException() {
        // Arrange
        when(jwtService.extractUsername(token)).thenReturn("maliciousUser123");
        when(userRepository.findByUsername("maliciousUser123")).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> myProfileService.getOrders(token));

        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("maliciousUser123");
    }

    @Test
    public void setUser_WhenUserExists_ShouldUpdateAndReturnUpdatedUser() {
        // Arrange
        UserMyProfileDto updatedUserDto = new UserMyProfileDto();
        updatedUserDto.setUsername("updatedUser");

        when(jwtService.extractUsername(token)).thenReturn("user123");
        when(userRepository.findByUsername("user123")).thenReturn(Optional.of(mockUser));
        when(userMapper.toDto(mockUser)).thenReturn(updatedUserDto);

        // Act
        UserMyProfileDto result = myProfileService.setUser(token, updatedUserDto);

        // Assert
        assertNotNull(result);
        assertEquals("updatedUser", result.getUsername());
        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("user123");
        verify(userRepository).save(mockUser);
        verify(userMapper).toDto(mockUser);
    }

    @Test
    public void setUser_WhenUserDoesNotExist_ShouldThrowException() {
        // Arrange
        UserMyProfileDto updatedUserDto = new UserMyProfileDto();

        // Act
        updatedUserDto.setUsername("updatedUser");

        when(jwtService.extractUsername(token)).thenReturn("maliciousUser123");
        when(userRepository.findByUsername("maliciousUser123")).thenReturn(Optional.empty());

        // Assert
        assertThrows(NoSuchElementException.class, () -> myProfileService.setUser(token, updatedUserDto));

        verify(jwtService).extractUsername(token);
        verify(userRepository).findByUsername("maliciousUser123");
    }
}

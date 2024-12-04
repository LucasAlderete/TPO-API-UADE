package tpo.uade.api.service;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.model.OrderModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.implementation.MyProfileService;
import tpo.uade.api.service.implementation.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyProfileServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserMyProfileMapper userMapper;
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private MyProfileService myProfileService;


    @Test
    void getUserWithOrders_Success() {
        // Arrange
        UserModel user = new UserModel();
        user.setUserId(1L);
        UserMyProfileDto userDto = new UserMyProfileDto();

        when(userService.getUserModelByUsername()).thenReturn(user);
        //when(orderRepository.findByUser_UserId(1L)).thenReturn(Optional.empty());
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Act
        UserMyProfileDto result = myProfileService.getUserWithOrders();

        // Assert
        assertNotNull(result);
        assertEquals(userDto, result);
        verify(userService).getUserModelByUsername();
        verify(orderRepository).findByUser_UserId(1L);
        verify(userMapper).toDto(user);
    }

    @Test
    void getUserOrders_ReturnsOrders() {
        // Arrange
        Long userId = 1L;
        OrderDto orderDto = new OrderDto();
        OrderModel OrderModel = new OrderModel();
        //when(orderRepository.findByUser_UserId(userId)).thenReturn(Optional.of(OrderModel));
        when(orderMapper.toDto(any())).thenReturn(orderDto);

        // Act
        List<OrderDto> result = myProfileService.getUserOrders(userId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(orderRepository).findByUser_UserId(userId);
    }

    @Test
    void getUserOrders_ReturnsOrdersEmpty() {
        // Arrange
        Long userId = 1L;
        //when(orderRepository.findByUser_UserId(userId)).thenReturn(Optional.empty());

        // Act
        List<OrderDto> result = myProfileService.getUserOrders(userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository).findByUser_UserId(userId);
    }

    @Test
    void setUser_Success() {
        // Arrange
        UserMyProfileDto userToUpdate = new UserMyProfileDto();
        userToUpdate.setUsername("newUsername");
        userToUpdate.setEmail("newEmail@test.com");
        userToUpdate.setName("NewName");
        userToUpdate.setSurname("NewSurname");
        userToUpdate.setBirthday(LocalDate.parse("2000-02-07"));

        UserModel existingUser = new UserModel();
        existingUser.setUserId(1L);

        UserMyProfileDto updatedUserDto = new UserMyProfileDto();
        updatedUserDto.setUsername("newUsername");

        when(userService.getUserModelByUsername()).thenReturn(existingUser);
        when(userMapper.toDto(existingUser)).thenReturn(updatedUserDto);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        UserMyProfileDto result = myProfileService.setUser(userToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("newUsername", result.getUsername());
        verify(userService).getUserModelByUsername();
        verify(userMapper).toDto(existingUser);
        verify(userRepository).save(existingUser);
    }

    @Test
    void setUser_UserNotFound() {
        // Arrange
        UserMyProfileDto userToUpdate = new UserMyProfileDto();
        when(userService.getUserModelByUsername()).thenThrow(new NoSuchElementException("User not found"));

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                myProfileService.setUser(userToUpdate));
        assertEquals("User not found", exception.getMessage());
        verify(userService).getUserModelByUsername();
        verifyNoInteractions(userRepository);
    }
}

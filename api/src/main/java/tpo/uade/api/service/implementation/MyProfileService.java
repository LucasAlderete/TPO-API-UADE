package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MyProfileService implements IMyProfileService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final UserMyProfileMapper userMapper;
    private final OrderMapper orderMapper;

    /**
     * Obtiene usuario con sus ordenes.
     *
     * @return usuario con ordenes en formato DTO.
     */
    @Override
    public UserMyProfileDto getUserWithOrders() {
        UserModel user = userService.getUserModelByUsername();
        List<OrderDto> orderList = getUserOrders(user.getUserId());
        UserMyProfileDto userDto = userMapper.toDto(user);
        userDto.setOrdersDto(orderList);
        return userDto;
    }

    /**
     * Obtiene las ordenes de un usuario.
     *
     * @param id  id del usuario.
     * @return lista de ordenes tipo DTO o lista vacia si el usuario no tiene ordenes aun.
     */
    @Override
    public List<OrderDto> getUserOrders(Long id) {
        return orderRepository
                .findByUser_UserId(id)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    /**
     * Actualiza un usuario.
     *
     * @param userToUpdate usuario DTO ingresado.
     * @return usuario DTO actualizado.
     * @throws NoSuchElementException si no encuentra al usuario.
     */
    @Override
    public UserMyProfileDto setUser(UserMyProfileDto userToUpdate) throws NoSuchElementException {
        UserModel user = userService.getUserModelByUsername();
        user.setUsername(userToUpdate.getUsername());
        user.setBirthday(userToUpdate.getBirthday());
        user.setEmail(userToUpdate.getEmail());
        user.setName(userToUpdate.getName());
        user.setSurname(userToUpdate.getSurname());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

}

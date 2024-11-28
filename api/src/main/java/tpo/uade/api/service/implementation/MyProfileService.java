package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyProfileService implements IMyProfileService {

    private UserService userService;
    private OrderRepository orderRepository;
    private UserMyProfileMapper userMapper;
    private OrderMapper orderMapper;

    @Override
    public UserMyProfileDto getUserWithOrders() {
        UserModel user = userService.getUserModelByUsername();
        List<OrderDto> orderList = getUserOrders(user.getUserId());
        UserMyProfileDto userMyProfile = userMapper.toDto(user);
        userMyProfile.setOrdersDto(orderList);
        return userMyProfile;
    }

    @Override
    public List<OrderDto> getUserOrders(Long id) {
        return orderRepository.findByUser_UserId(id).stream()
                .map(orderMapper::toDto)
                .toList();
    }
    @Override
    //Cambiar forma de hacerlo
    public UserMyProfileDto setUser(String token, UserMyProfileDto updatedUser) {
        String username = jwtService.extractUsername(token);
        System.out.println(updatedUser);
        UserModel currentUser = userRepository.findByUsername(username).orElseThrow((() -> new NoSuchElementException("user doesn't exist")));
        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setBirthday(updatedUser.getBirthday());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setName(updatedUser.getName());
        currentUser.setSurname(updatedUser.getSurname());
        userRepository.save(currentUser);
        return userMapper.toDto(currentUser);
    }

}

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

@RequiredArgsConstructor
@Service
public class MyProfileService implements IMyProfileService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final UserMyProfileMapper userMapper;
    private final OrderMapper orderMapper;

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
    public UserMyProfileDto setUser(UserMyProfileDto userToUpdate) {
        UserModel currentUser = userService.getUserModelByUsername();
        currentUser.setUsername(userToUpdate.getUsername());
        currentUser.setBirthday(userToUpdate.getBirthday());
        currentUser.setEmail(userToUpdate.getEmail());
        currentUser.setName(userToUpdate.getName());
        currentUser.setSurname(userToUpdate.getSurname());
        userRepository.save(currentUser);
        return userMapper.toDto(currentUser);
    }

}

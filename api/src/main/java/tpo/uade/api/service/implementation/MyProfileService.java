package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tpo.uade.api.config.JwtService;
import tpo.uade.api.dto.UserDto;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.model.OrderModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
}

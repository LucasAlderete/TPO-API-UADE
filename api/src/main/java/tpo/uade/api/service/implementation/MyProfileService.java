package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpo.uade.api.dto.UserMyProfileDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.mapper.OrderMapper;
import tpo.uade.api.mapper.UserMyProfileMapper;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyProfileService implements IMyProfileService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private UserMyProfileMapper userMapper;
    private OrderMapper orderMapper;

    public UserMyProfileDto getUser(Long id){
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("user doesn't exist")));
    }

    public List<OrderDto> getOrders(Long id){
         return orderRepository.findByUser_UserId(id).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}

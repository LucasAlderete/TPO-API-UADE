package tpo.uade.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tpo.uade.api.dto.CheckoutsByUserDto;
import tpo.uade.api.dto.InfoUserDto;
import tpo.uade.api.dto.MyProfileDto;
import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.mapper.MyProfileMapper;
import tpo.uade.api.repository.OrderRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IMyProfileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyProfileService implements IMyProfileService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private MyProfileMapper mapper;

    public MyProfileDto getMyProfileDtoById(Long id) {
        var user = getInfoUserDto(id);
        var checkouts = getCheckoutsById(id);
        return MyProfileDto.builder()
                .user(user)
                .checkouts(checkouts)
                .build();
    }

    private InfoUserDto getInfoUserDto(Long id){
        return mapper.toInfoUserDto(userRepository.findById(id).orElseThrow());
    }

    private CheckoutsByUserDto getCheckoutsById(Long id){
        List<OrderDto> checkouts = orderRepository.findByUserId(id).stream().map(mapper::toOrderDto).collect(Collectors.toList());
        return CheckoutsByUserDto.builder()
                .checkouts(checkouts)
                .build();
    }
}

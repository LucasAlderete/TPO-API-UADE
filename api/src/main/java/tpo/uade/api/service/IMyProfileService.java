package tpo.uade.api.service;

import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.dto.UserMyProfileDto;

import java.util.List;

public interface IMyProfileService {
    UserMyProfileDto getUser(Long id);
    List<OrderDto> getOrders(Long id);
}

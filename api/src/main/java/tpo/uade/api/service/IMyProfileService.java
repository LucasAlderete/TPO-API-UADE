package tpo.uade.api.service;

import tpo.uade.api.dto.OrderDto;
import tpo.uade.api.dto.UserMyProfileDto;

import java.util.List;

public interface IMyProfileService {
    UserMyProfileDto getUserWithOrders();
    List<OrderDto> getUserOrders(Long id);
    UserMyProfileDto setUser(UserMyProfileDto userToUpdate);
}

package tpo.uade.api.service;

import tpo.uade.api.dto.UserDto;

public interface IUserService {
    UserDto getUserByUsername (String token);
    void createUser(UserDto userDto);
}

package tpo.uade.api.service;

import tpo.uade.api.dto.UserDto;

public interface IUserService {
    UserDto getUserData (String username);
    void createUser(UserDto userDto);
}

package tpo.uade.api.service;

import tpo.uade.api.dto.UserDto;

public interface IGetUserDataService {
    UserDto getUserData (String username);
}

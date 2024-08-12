package tpo.uade.api.service;

import tpo.uade.api.dto.UserDTO;

public interface IGetUserDataService {
    UserDTO getUserData (String username);
}

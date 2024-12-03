package tpo.uade.api.service;

import tpo.uade.api.dto.user.UserRequestDto;

public interface ICreateUserService {
    void createUser (UserRequestDto userRequestDTO);
}

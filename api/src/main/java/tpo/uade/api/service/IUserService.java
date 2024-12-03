package tpo.uade.api.service;

import tpo.uade.api.dto.user.UserRequestDto;
import tpo.uade.api.dto.user.UserResponseDto;
import tpo.uade.api.model.UserModel;

public interface IUserService {
    UserModel getUserModelByUsername ();

    UserResponseDto getUserDtoByUsername ();

    Long getUserIdByUsername ();

    void createUser (UserRequestDto userRequestDto);
}
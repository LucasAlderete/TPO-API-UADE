package tpo.uade.api.service;

import tpo.uade.api.dto.UserDto;
import tpo.uade.api.model.UserModel;

public interface IUserService {
    UserModel getUserModelByUsername ();

    UserDto getUserDtoByUsername ();

    Long getUserIdByUsername ();

    void createUser (UserDto userDto);
}
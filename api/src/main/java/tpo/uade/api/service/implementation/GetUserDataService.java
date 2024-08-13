package tpo.uade.api.service.implementation;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.UserDto;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IGetUserDataService;

import java.util.NoSuchElementException;

@Service
public class GetUserDataService implements IGetUserDataService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public GetUserDataService (UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    //TODO -> documentation
    public UserDto getUserData (String username) throws NoSuchElementException {
        UserModel userModelDB = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return userMapper.mapFromDatabaseEntity(userModelDB);
    }
}

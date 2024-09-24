package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tpo.uade.api.config.JwtService;
import tpo.uade.api.dto.UserDto;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.model.CartModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.CartRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IUserService;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Gets a user details
     * @param token a valid token
     * @return UserDto
     */
    @Override
    public UserDto getUserByUsername (String token) throws NoSuchElementException {
        String username = jwtService.extractUsername(token);
        UserModel userModelDB = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return userMapper.mapFromDatabaseEntity(userModelDB);
    }

    /**
     * Creates a new user in the database
     * @param userDTO a valid user
     * @return UserModel
     */
    @Override
    public void createUser (UserDto userDTO) {
        UserModel savedUser = userRepository.save(userMapper.mapToDatabaseEntity(userDTO));
    }
}

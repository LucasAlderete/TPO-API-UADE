package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tpo.uade.api.dto.UserDto;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IUserService;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    /**
     * Gets a user details
     * @param username a valid username to search for a user
     * @return UserDto
     */
    @Override
    public UserDto getUserByUsername (String username) throws NoSuchElementException {
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
        userRepository.save(userMapper.mapToDatabaseEntity(userDTO));
    }
}

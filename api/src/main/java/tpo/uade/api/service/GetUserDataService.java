package tpo.uade.api.service;

import org.springframework.stereotype.Service;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.model.database.UserDB;
import tpo.uade.api.model.frontend.User;
import tpo.uade.api.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
public class GetUserDataService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public GetUserDataService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    //TODO -> documentation
    public User getUserData (String username) throws NoSuchElementException {
        UserDB userDB = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return userMapper.mapFromDatabaseEntity(userDB);
    }
}

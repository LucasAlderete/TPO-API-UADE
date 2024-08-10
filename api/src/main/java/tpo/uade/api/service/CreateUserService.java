package tpo.uade.api.service;

import org.springframework.stereotype.Service;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.model.frontend.User;
import tpo.uade.api.repository.UserRepository;

@Service
public class CreateUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public CreateUserService (UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    //TODO -> documentation
    public void createUser (User user) {
        userRepository.save(userMapper.mapToDatabaseEntity(user));
    }
}

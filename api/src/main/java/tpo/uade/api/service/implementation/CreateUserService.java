package tpo.uade.api.service.implementation;

import org.springframework.stereotype.Service;
import tpo.uade.api.mapper.UserMapper;
import tpo.uade.api.dto.UserDTO;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.ICreateUserService;

@Service
public class CreateUserService implements ICreateUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public CreateUserService (UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    //TODO -> documentation
    public void createUser (UserDTO userDTO) {
        userRepository.save(userMapper.mapToDatabaseEntity(userDTO));
    }
}

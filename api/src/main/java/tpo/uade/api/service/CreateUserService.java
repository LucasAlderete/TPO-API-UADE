package tpo.uade.api.service;

import org.springframework.stereotype.Service;
import tpo.uade.api.repository.UserRepository;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    public CreateUserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO -> documentation
    public void createUser () {
        //TODO -> define operations to create a user
    }
}

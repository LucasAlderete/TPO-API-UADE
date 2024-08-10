package tpo.uade.api.service;

import org.springframework.stereotype.Service;
import tpo.uade.api.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String authenticateUser () { //TODO -> determine credentials
        return ""; //TODO -> generate JWT
    }
}

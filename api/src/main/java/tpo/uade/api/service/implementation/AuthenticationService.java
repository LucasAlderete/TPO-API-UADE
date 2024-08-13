package tpo.uade.api.service.implementation;

import org.springframework.stereotype.Service;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String authenticateUser () { //TODO -> determine credentials
        return ""; //TODO -> generate JWT
    }
}

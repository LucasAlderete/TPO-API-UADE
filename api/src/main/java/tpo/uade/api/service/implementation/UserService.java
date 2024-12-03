package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tpo.uade.api.dto.user.UserRequestDto;
import tpo.uade.api.dto.user.UserResponseDto;
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
     * @return UserRequestDto
     */
    @Override
    public UserModel getUserModelByUsername () throws NoSuchElementException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
    }

    @Override
    public UserResponseDto getUserDtoByUsername () throws NoSuchElementException {
        UserResponseDto urdto = userMapper.mapFromDatabaseEntity(getUserModelByUsername());
        System.out.println(urdto);
        return urdto;
    }

    @Override
    public Long getUserIdByUsername () throws NoSuchElementException {
        return getUserModelByUsername().getUserId();
    }

    @Override
    public void createUser (UserRequestDto userRequestDTO) {
        userRepository.save(userMapper.mapToDatabaseEntity(userRequestDTO));
    }
}

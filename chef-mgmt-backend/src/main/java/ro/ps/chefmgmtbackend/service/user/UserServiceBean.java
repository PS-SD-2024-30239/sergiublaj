package ro.ps.chefmgmtbackend.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.ps.chefmgmtbackend.dto.user.UserResponseDTO;
import ro.ps.chefmgmtbackend.exception.ExceptionCode;
import ro.ps.chefmgmtbackend.exception.NotFoundException;
import ro.ps.chefmgmtbackend.mapper.UserMapper;
import ro.ps.chefmgmtbackend.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userEntityToUserResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR002_EMAIL_NOT_FOUND.getMessage(),
                        email
                )));
    }
}

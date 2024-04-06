package ro.ps.chefmgmtbackend.service.user;

import ro.ps.chefmgmtbackend.dto.user.UserResponseDTO;

public interface UserService {

    UserResponseDTO findByEmail(String email);
}

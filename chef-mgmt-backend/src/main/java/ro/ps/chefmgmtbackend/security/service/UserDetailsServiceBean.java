package ro.ps.chefmgmtbackend.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.ps.chefmgmtbackend.exception.ExceptionCode;
import ro.ps.chefmgmtbackend.model.UserEntity;
import ro.ps.chefmgmtbackend.repository.UserRepository;

@RequiredArgsConstructor
public class UserDetailsServiceBean implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(this::getUserDetails)
                .orElseThrow(() -> new BadCredentialsException(ExceptionCode.ERR099_INVALID_CREDENTIALS.getMessage()));
    }

    private UserDetails getUserDetails(UserEntity user) {
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}

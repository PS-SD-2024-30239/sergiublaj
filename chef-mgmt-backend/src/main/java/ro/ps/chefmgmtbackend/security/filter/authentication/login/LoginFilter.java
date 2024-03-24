package ro.ps.chefmgmtbackend.security.filter.authentication.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.ps.chefmgmtbackend.dto.auth.LoginRequestDTO;
import ro.ps.chefmgmtbackend.security.util.SecurityConstants;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public LoginFilter(
            ObjectMapper objectMapper,
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler
    ) {
        this.objectMapper = objectMapper;
        this.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
        this.setAuthenticationSuccessHandler(loginSuccessHandler);
        this.setAuthenticationFailureHandler(loginFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO authenticationRequest = objectMapper.readValue(
                    request.getInputStream(),
                    LoginRequestDTO.class
            );
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            );

            return super.getAuthenticationManager().authenticate(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new BadCredentialsException(e.getMessage());
        }
    }
}
package ro.ps.chefmgmtbackend.security.filter.authentication.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ro.ps.chefmgmtbackend.security.util.JwtUtil;
import ro.ps.chefmgmtbackend.security.util.SecurityConstants;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String accessToken = JwtUtil.generateJwtToken(
                ((User) authentication.getPrincipal()).getUsername(),
                authentication.getAuthorities()
        );
        response.addCookie(JwtUtil.buildCookie(SecurityConstants.JWT_TOKEN, accessToken));

        response.setStatus(HttpStatus.OK.value());
    }
}
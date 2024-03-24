package ro.ps.chefmgmtbackend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
@RequiredArgsConstructor
public class AccessDeniedHandlerBean implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AccessDeniedException exception
    ) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        try {
            objectMapper.writeValue(response.getWriter(), exception.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
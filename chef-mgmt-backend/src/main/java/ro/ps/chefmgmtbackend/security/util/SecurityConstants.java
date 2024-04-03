package ro.ps.chefmgmtbackend.security.util;

public final class SecurityConstants {

    public static final Integer PASSWORD_STRENGTH = 10;
    public static final String AUTH_PATHS_TO_SKIP = "/auth/**";
    public static final String LOGIN_URL = "/auth/v1/login";
    public static final String JWT_TOKEN = "jwt-token";
    public static final String[] SWAGGER_PATHS_TO_SKIP = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}

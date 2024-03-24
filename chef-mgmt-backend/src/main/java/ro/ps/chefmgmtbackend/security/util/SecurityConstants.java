package ro.ps.chefmgmtbackend.security.util;

public final class SecurityConstants {

    public static final Integer SALT = 10;
    public static final String AUTH_PATHS_TO_SKIP = "/auth/**";
    public static final String LOGIN_URL = "/auth/v1/login";
    public static final String JWT_TOKEN = "jwt-token";
}

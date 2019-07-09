package wg.app.web.security;

public interface JwtConfig {

    String SECRET = "wojczehgylewskybuziak123";
    long EXPIRATION_TIME = 864_000_000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}

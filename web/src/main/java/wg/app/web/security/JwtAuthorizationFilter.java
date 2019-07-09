package wg.app.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import wg.app.model.exceptions.MyException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtConfig.HEADER_STRING);
        if (header == null || !header.startsWith(JwtConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response)
    {
        try {

            String token = request.getHeader(JwtConfig.HEADER_STRING);
            System.out.println(token);
            if (token != null) {

                Claims claims = Jwts
                        .parser()
                        .setSigningKey(JwtConfig.SECRET)
                        .parseClaimsJws(token.replace(JwtConfig.TOKEN_PREFIX, ""))
                        .getBody();

                String username = claims.getSubject();
                System.out.println(username);
                List<GrantedAuthority> roles = Arrays.asList(claims.get("roles").toString().split(",")).stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

                if (username == null) {
                    throw new MyException("USERNAME FROM JWT TOKEN IS NOT CORRECT");
                }

                return new UsernamePasswordAuthenticationToken(username, null, roles);
            }
            return null;

        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new MyException("TOKEN PARSE EXCEPTION");
        }
    }
}

package swd.SWDProject.config.sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import swd.SWDProject.entity.Role;
import swd.SWDProject.entity.User;
import swd.SWDProject.repository.UserRepository;
import swd.SWDProject.service.imp.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static swd.SWDProject.config.sercurity.SecurityConstants.AUTHORITIES_KEY;
import static swd.SWDProject.config.sercurity.SecurityConstants.HEADER_STRING;
import static swd.SWDProject.config.sercurity.SecurityConstants.JWT_SECRET;
import static swd.SWDProject.config.sercurity.SecurityConstants.TOKEN_PREFIX;


public class JWTVerifier extends BasicAuthenticationFilter {

    public static String USERNAME = "";

    public JWTVerifier(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String tokenHeader = request.getHeader(HEADER_STRING);

        if(tokenHeader != null){
            String token = tokenHeader.replace(TOKEN_PREFIX, "");

            String username = getDecodedJWT(token).getSubject();

            USERNAME = username;

            if(username != null){

                Collection<GrantedAuthority> authorities =
                        Arrays.stream(getDecodedJWT(token).
                                getClaim(AUTHORITIES_KEY)
                                .asString().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
        }
        return null;
    }

    private DecodedJWT getDecodedJWT(String token) {
        return JWT.require(Algorithm.HMAC512(JWT_SECRET.getBytes()))
                .build()
                .verify(token);
    }
}

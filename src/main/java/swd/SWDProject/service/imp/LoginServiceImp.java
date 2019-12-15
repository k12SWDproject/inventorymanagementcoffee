package swd.SWDProject.service.imp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.FirebaseConfig;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.User;
import swd.SWDProject.repository.UserRepository;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import static swd.SWDProject.config.sercurity.SecurityConstants.AUTHORITIES_KEY;
import static swd.SWDProject.config.sercurity.SecurityConstants.EXPIRATION_TIME;
import static swd.SWDProject.config.sercurity.SecurityConstants.JWT_SECRET;

@Slf4j
@Service
@Transactional
public class LoginServiceImp implements swd.SWDProject.service.LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String loginGoogleService(String userId) throws IOException, FirebaseAuthException, AuthenticationException {
        try {
            log.info(StringRS.BEGIN_SERVICE + "login Google Service");
            if(userId == null ) {
                throw new JsonParseException("a");
            }

            UserRecord ggUser = FirebaseConfig.firebaseAuth.getUser(userId);
            if(ggUser == null ) {
                throw new AuthenticationException();
            }
            User user =userRepository.findUserByUserGGId(ggUser.getUid());
            if(user == null) {
                userRepository.save(User.builder()
                        .status(1)
                        .fullName(ggUser.getDisplayName())
                        .username(ggUser.getEmail())
                        .email(ggUser.getEmail())
                        .userGGId(ggUser.getUid())
                        .build()
                );
                return "";
            } else {
                String rs =  JWT.create().withSubject(user.getUsername())
                        .withClaim(AUTHORITIES_KEY, "admin")
                        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .sign(Algorithm.HMAC512(JWT_SECRET.getBytes()));
                log.debug(rs);
                return "Bearer "+ rs;
            }
        }finally {
            log.info(StringRS.BEGIN_SERVICE + "login Google Service");
        }
    }
}

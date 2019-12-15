package swd.SWDProject.controller;

import com.google.firebase.auth.FirebaseAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.model.GoogleLoginDTO;
import swd.SWDProject.service.LoginService;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @PostMapping(value = "/google")
    public ResponseEntity loginGoogle(@RequestBody GoogleLoginDTO token) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER + "Login Gooogle");
            String rs = loginService.loginGoogleService(token.getUid());
            if(rs.length() == 0 ){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.ok(rs);
        } catch (IOException e) {
            return  ResponseEntity.badRequest().build();
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(403).build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).build();
        } finally {
            log.info(StringRS.BEGIN_CONTROLLER + "Login Gooogle");
        }
    }

}

package swd.SWDProject.service;

import com.google.firebase.auth.FirebaseAuthException;

import javax.naming.AuthenticationException;
import java.io.IOException;

public interface LoginService {
    String loginGoogleService(String userId) throws IOException, FirebaseAuthException, AuthenticationException;
}

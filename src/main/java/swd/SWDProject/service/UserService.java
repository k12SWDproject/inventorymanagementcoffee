package swd.SWDProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String getRoleByUserNameAndPassword(String username, String password) throws Exception {
        try{
            String role = userRepository.getRoleByPasswordAndUsername(password,username);
            return role;
        }finally {

        }
    }

}

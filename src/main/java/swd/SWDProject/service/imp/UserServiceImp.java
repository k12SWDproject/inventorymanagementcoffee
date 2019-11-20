package swd.SWDProject.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.entity.User;
import swd.SWDProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements swd.SWDProject.service.UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String getRoleByUserNameAndPassword(String username, String password) {
        try{
            String role = userRepository.getRoleByPasswordAndUsername(password,username);
            return role;
        }finally {

        }
    }

    @Override
    public User createUser(User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }finally {

        }
    }

    @Override
    public User getUserByUserName(String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            return user;
        }finally {

        }
    }

}

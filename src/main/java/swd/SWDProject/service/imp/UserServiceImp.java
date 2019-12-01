package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.UserFilter;
import swd.SWDProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
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

    @Override
    public List<User> getUsers(String filter) throws JsonProcessingException {
//        try {
//            log.info(StringRS.BEGIN_SERVICE + "getUsers");
//            List<User> users = null;
//            UserFilter userFilter = new ObjectMapper().readValue(filter, UserFilter.class);
//            List<Specification<User>> list = null;
//            userRepository.find(list);
//
//            return users;
//        }finally {
//            log.info(StringRS.END_SERVICE + "getUsers");
//
//        }
        return null;
    }

}

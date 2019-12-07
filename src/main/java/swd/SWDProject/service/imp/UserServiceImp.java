package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.dto.UserUpdateDTO;
import swd.SWDProject.entity.House;
import swd.SWDProject.entity.House_;
import swd.SWDProject.entity.SpecificationBuilder;
import swd.SWDProject.entity.User;
import swd.SWDProject.entity.User_;
import swd.SWDProject.filter.UserFilter;
import swd.SWDProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        try {
            log.info(StringRS.BEGIN_SERVICE + "getUsers");
            List<User> users;
            UserFilter userFilter = new ObjectMapper().readValue(filter, UserFilter.class);
            List<Specification<User>> list = new ArrayList<>();

            if( userFilter.getHouseId() != 0) {
                list.add(new Specification<User>() {
                    @Override
                    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        Join<User, House> house = root.join(User_.house);
                        return criteriaBuilder.equal(house.get(House_.id), userFilter.getHouseId());
                    }
                });
            }

            if (userFilter.getEmail() != null) {
                list.add(((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder.equal(root.get(User_.username), userFilter.getEmail());
                }));
            }
            
            users = userRepository.findAll(SpecificationBuilder.build(list));

            return users;
        }finally {
            log.info(StringRS.END_SERVICE + "getUsers");

        }
    }

    @Override
    public User updateUser(UserUpdateDTO userUpdateDTO) throws Exception {
        try {
            log.info( StringRS.BEGIN_SERVICE + "update UserService");
            User user = userRepository.findUserByUsername(userUpdateDTO.getEmail());
            if(user == null) {
                throw new Exception();
            }
            user.setDateOfBirth(userUpdateDTO.getBirthDay());
            user.setFullName(userUpdateDTO.getFullname());
            user.setGender(userUpdateDTO.getGender());
            User rs = userRepository.save(user);
            return  rs;
        }finally {
            log.info( StringRS.END_SERVICE + "update UserService");
        }
    }

}

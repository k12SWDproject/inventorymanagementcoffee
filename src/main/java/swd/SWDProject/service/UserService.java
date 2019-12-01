package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.entity.User;

import java.util.List;

public interface UserService {

    String getRoleByUserNameAndPassword(String username, String password);

    User createUser(User user);

    User getUserByUserName(String username);

    List<User> getUsers(String filter) throws JsonProcessingException;

}

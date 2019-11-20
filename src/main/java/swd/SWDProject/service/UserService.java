package swd.SWDProject.service;

import swd.SWDProject.entity.User;

public interface UserService {

    String getRoleByUserNameAndPassword(String username, String password);

    User createUser(User user);

    User getUserByUserName(String username);

}

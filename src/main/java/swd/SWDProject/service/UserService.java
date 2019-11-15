package swd.SWDProject.service.imp;

import swd.SWDProject.entity.User;

public interface UserService {

    public String getRoleByUserNameAndPassword(String username, String password);

    public User createUser(User user);

}

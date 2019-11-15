package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public String getRoleByPasswordAndUsername(String password, String userName);

    public User findUserByUsername(String username);

}

package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

     String getRoleByPasswordAndUsername(String password, String userName);

     User findUserByUsername(String username);

}

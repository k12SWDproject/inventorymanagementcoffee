package swd.SWDProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.entity.User;
import swd.SWDProject.service.imp.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestBody User user) {
        try{
            User createdUser = userService.createUser(user);
            return new ResponseEntity(createdUser, HttpStatus.OK);
        }finally {

        }
    }
//
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        try{
            User user = userService.getUserByUserName(username);
            return new ResponseEntity(user, HttpStatus.OK);
        }finally {

        }
    }

}

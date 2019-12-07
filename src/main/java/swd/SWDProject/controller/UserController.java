package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.dto.UserUpdateDTO;
import swd.SWDProject.entity.User;
import swd.SWDProject.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestBody User user) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"createAccount");
            User createdUser = userService.createUser(user);
            return new ResponseEntity(createdUser, HttpStatus.OK);
        }finally {
            log.info(StringRS.END_CONTROLLER+"createAccount");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserUpdateDTO userupdateDTO) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"createAccount");
            User createdUser = userService.updateUser(userupdateDTO);
            return new ResponseEntity(createdUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER+"createAccount");
        }
    }
//
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"createAccount");
            User user = userService.getUserByUserName(username);
            return new ResponseEntity(user, HttpStatus.OK);
        }finally {
            log.info(StringRS.END_CONTROLLER+"createAccount");
        }
    }

    @GetMapping
    public ResponseEntity getUsers(String filter) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "getUsers");
            List<User> rs = null;
            rs = userService.getUsers(filter);
            return ResponseEntity.ok(rs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER + "getUsers");
        }
    }



}

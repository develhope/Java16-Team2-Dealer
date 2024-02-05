package com.develhope.spring.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }



}

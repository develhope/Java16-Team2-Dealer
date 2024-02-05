package com.develhope.spring.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable long id) {
        Optional<UserDTO> userSearched = userService.getUserById(id);
        if(userSearched.isPresent()) {
            return new ResponseEntity<>(userSearched, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userSearched, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAdmins")
    public ResponseEntity<List<UserDTO>> getAllAdmins() {
        return new ResponseEntity<>(userService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        return new ResponseEntity<>(userService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/getSellers")
    public ResponseEntity<List<UserDTO>> getAllSellers() {
        return new ResponseEntity<>(userService.getAllSellers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> putUser (@PathVariable long id, @RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }
}

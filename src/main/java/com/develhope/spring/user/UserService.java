package com.develhope.spring.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserRepository userRepository;

    public ResponseEntity<User> addUser(UserDTO user) {
        ModelMapper modelMapper = new ModelMapper();

        User userEntity = modelMapper.map(user, User.class);
        return new ResponseEntity<>(userRepository.saveAndFlush(userEntity), HttpStatus.CREATED);
        /*User userEntity = modelMapper.map(user, User.class);
        System.out.println("CIAO userEntity");
        System.out.println(userEntity);
        return new ResponseEntity<>(userRepository.saveAndFlush(userEntity), HttpStatus.CREATED);*/
    }
}

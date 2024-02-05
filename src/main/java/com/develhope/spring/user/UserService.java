package com.develhope.spring.user;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserDTO addUser(UserDTO user) {

        UserEntity userEntityToSave = modelMapper.map(user, UserEntity.class);
        UserEntity userSaved = userRepository.saveAndFlush(userEntityToSave);
        return modelMapper.map(userSaved, UserDTO.class);
    }


}

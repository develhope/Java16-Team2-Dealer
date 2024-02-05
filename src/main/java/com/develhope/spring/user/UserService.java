package com.develhope.spring.user;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;



    public UserDTO addUser(UserDTO user) {

        UserEntity userEntityToSave = user.toEntity();
        UserEntity userSaved = userRepository.saveAndFlush(userEntityToSave);
        return userSaved.toDto();
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(UserEntity::toDto).toList();
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<UserEntity> userSearched = userRepository.findById(id);
        return userSearched.map(UserEntity::toDto);
    }


}

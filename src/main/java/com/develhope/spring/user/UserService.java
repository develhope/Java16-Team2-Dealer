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

    public List<UserDTO> getAllAdmins() {
        List<UserEntity> userEntities = userRepository.findUsersByRole(UserRole.ADMIN.toString());
        return userEntities.stream().map(UserEntity::toDto).toList();
    }

    public List<UserDTO> getAllSellers() {
        List<UserEntity> userEntities = userRepository.findUsersByRole(UserRole.SELLER.toString());
        return userEntities.stream().map(UserEntity::toDto).toList();
    }

    public List<UserDTO> getAllCustomers() {
        List<UserEntity> userEntities = userRepository.findUsersByRole(UserRole.CUSTOMER.toString());
        return userEntities.stream().map(UserEntity::toDto).toList();
    }

    ResponseEntity<UserResponse> deleteUserById(Long id) {
        boolean userExist = userRepository.existsById(id);
        if (userExist) {
            userRepository.deleteById(id);
            UserResponse.UserDeletedSuccessfully response =
                    new UserResponse.UserDeletedSuccessfully("User successfully deleted.");

            return UserResponse.mapResponseEntity(response);
        } else {
            UserResponse.UserGenericError response =
                    new UserResponse.UserGenericError("Error while deleting user with ID: " + id);

            return UserResponse.mapResponseEntity(response);
        }
    }


}

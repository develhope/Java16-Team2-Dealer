package com.develhope.spring.features.user.services;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public UserResponseDto createUser(UserRequestDto newUser, Role role) {

        UserModel newUserModel = UserModel.convertRequestToModel(newUser);
        newUserModel.setRole(role);
        UserEntity newUserEntity = UserModel.convertModelToEntity(newUserModel);
        UserEntity savedUser = userRepository.saveAndFlush(newUserEntity);
        UserModel savedUserModel = UserModel.convertEntityToModel(savedUser);
        return UserModel.convertModelToResponse(savedUserModel);

    }


    public List<UserResponseDto> getAllByRole(Role role) {
        List<UserEntity> admins = userRepository.findByRole(role);
        List<UserModel> adminsModel = UserModel.convertEntityListToModelList(admins);
        return UserModel.convertModelListToResponseList(adminsModel);
    }
}

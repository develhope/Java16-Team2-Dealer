package com.develhope.spring.features.user;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDTO;
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

    public UserResponseDTO createAdmin(UserRequestDto newAdmin) {

        UserModel newAdminModel = UserModel.convertRequestToModel(newAdmin);
        newAdminModel.setRole(Role.ADMIN);
        UserEntity newAdminEntity = UserModel.convertModelToEntity(newAdminModel);
        UserEntity savedAdmin = userRepository.saveAndFlush(newAdminEntity);
        UserModel savedAdminModel = UserModel.convertEntityToModel(savedAdmin);
        return UserModel.convertModelToResponse(savedAdminModel);

    }

    @Override
    public List<UserEntity> getAllAdmins() {
        return userRepository.findByRole(Role.ADMIN);
    }
}

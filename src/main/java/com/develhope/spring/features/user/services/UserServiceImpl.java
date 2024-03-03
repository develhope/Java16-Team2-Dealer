package com.develhope.spring.features.user.services;

import com.develhope.spring.features.user.dto.RoleDto;
import com.develhope.spring.features.user.dto.UserErrorDto;
import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder pwEncoder = new BCryptPasswordEncoder();


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

    @Override
    public Either<UserErrorDto, UserResponseDto> createUser(UserRequestDto newUser, UserEntity userLogged) {

        UserModel newUserModel = UserModel.convertRequestToModel(newUser);
        UserEntity newUserEntity = UserModel.convertModelToEntity(newUserModel);

        if(checkAuthorityLevel(userLogged, newUserEntity)) {
            newUserEntity.setPassword(pwEncoder.encode(newUserEntity.getPassword()));
            UserEntity savedUser = userRepository.saveAndFlush(newUserEntity);
            UserModel savedUserModel = UserModel.convertEntityToModel(savedUser);
            return Either.right(UserModel.convertModelToResponse(savedUserModel)) ;
        } else {
            return Either.left(new UserErrorDto(403, "You don't have the permission to create this user"));
        }
    }

    @Override
    public Either<UserErrorDto, List<UserResponseDto>> getAllByRole(UserEntity userLogged, RoleDto roleDto) {

        if(checkAuthorityLevel(userLogged, roleDto.getRole())) {
            List<UserEntity> users = userRepository.findByRole(roleDto.getRole());
            List<UserModel> usersModel = UserModel.convertEntityListToModelList(users);
            List<UserResponseDto> usersDto = UserModel.convertModelListToResponseList(usersModel);
            return Either.right(usersDto);
        } else {
            return Either.left(new UserErrorDto(403, "You don't have the permission to get these users"));
        }
    }

    @Override
   public Either<UserErrorDto, Boolean> deleteUser(Long userToDeleteId, UserEntity userLogged) {
       Optional<UserEntity> userToDeleteOpt = userRepository.findById(userToDeleteId);

       if(userToDeleteOpt.isPresent()) {
           UserEntity userToDelete = userToDeleteOpt.get();
           if(checkAuthorityLevel(userLogged, userToDelete)) {
               try{
                   userToDelete.setActive(false);
                   userRepository.saveAndFlush(userToDelete);
                   //userRepository.deleteById(userToDeleteId);
                   return Either.right(true);
               } catch (Exception ex) {
                   return Either.left(new UserErrorDto(419, "Error while deleting the user: " + ex.getMessage()));
               }
           } else {
               return Either.left(new UserErrorDto(403, "You don't have the permission to delete this user"));
           }
       } else {
           return Either.left(new UserErrorDto(404, "User to be deleted not found"));
       }
   }


    @Override
    public Either<UserErrorDto, UserResponseDto> getUserById(Long id, UserEntity userLogged) {
        Optional<UserEntity> userSearchedOpt = userRepository.findById(id);

        if(userSearchedOpt.isPresent()) {
            UserEntity userSearched = userSearchedOpt.get();
            if(checkAuthorityLevel(userLogged, userSearched)) {
                UserModel model = UserModel.convertEntityToModel(userSearched);
                UserResponseDto response = UserModel.convertModelToResponse(model);
                return Either.right(response);
            } else {
                return Either.left(new UserErrorDto(403, "You don't have the permission to search this user"));
            }
        } else{
            return Either.left(new UserErrorDto(404, "User searched not found"));
        }
    }


    private boolean checkAuthorityLevel(UserEntity loggedUser, UserEntity userToModify) {
        return ( (!loggedUser.getRole().equals(Role.CUSTOMER)) && (loggedUser.getRole().authorityLevel >= userToModify.getRole().authorityLevel));
   }

    private boolean checkAuthorityLevel(UserEntity loggedUser, Role role) {
        return ( (!loggedUser.getRole().equals(Role.CUSTOMER) ) && (loggedUser.getRole().authorityLevel >= role.authorityLevel)) ;
    }


}

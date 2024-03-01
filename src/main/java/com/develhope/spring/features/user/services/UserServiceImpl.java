package com.develhope.spring.features.user.services;

import com.develhope.spring.features.user.dto.UserErrorDto;
import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.repository.UserRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


   private boolean checkAuthorityLevel(UserEntity loggedUser, UserEntity userToModify) {
        return loggedUser.getRole().authorityLevel >= userToModify.getRole().authorityLevel;
   }
}

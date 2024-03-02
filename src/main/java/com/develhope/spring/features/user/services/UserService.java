package com.develhope.spring.features.user.services;

import com.develhope.spring.features.user.dto.RoleDto;
import com.develhope.spring.features.user.dto.UserErrorDto;
import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import io.vavr.control.Either;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    Either<UserErrorDto, List<UserResponseDto>>  getAllByRole(UserEntity userLogged, RoleDto role);

    Either<UserErrorDto, UserResponseDto> createUser(UserRequestDto newUser, UserEntity userLogged);

    Either<UserErrorDto, Boolean> deleteUser(Long userToDeleteId, UserEntity userLogged);
}

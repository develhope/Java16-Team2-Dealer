package com.develhope.spring.features.user.services;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<UserResponseDto> getAllByRole(Role role);

    UserResponseDto createUser(UserRequestDto newUser, Role role);
}

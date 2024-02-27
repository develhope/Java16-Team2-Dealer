package com.develhope.spring.features.user;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDTO;
import com.develhope.spring.features.user.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();


}

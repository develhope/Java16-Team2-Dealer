package com.develhope.spring.features.user.model;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDTO;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean active;
    private Role role;

    public static UserModel convertRequestToModel(UserRequestDto requestDto) {
        return UserModel.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .active(requestDto.isActive())
                .build();
    }

    public static UserEntity convertModelToEntity(UserModel model) {
        return UserEntity.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .phoneNumber(model.getPhoneNumber())
                .email(model.getEmail())
                .password(model.getPassword())
                .active(model.isActive())
                .role(model.getRole())
                .build();
    }

    public static UserModel convertEntityToModel(UserEntity entity) {
        return UserModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .active(entity.isActive())
                .role(entity.getRole())
                .build();
    }

    public static UserResponseDTO convertModelToResponse(UserModel model) {
        return UserResponseDTO.builder()
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .phoneNumber(model.getPhoneNumber())
                .email(model.getEmail())
                .password(model.getPassword())
                .active(model.isActive())
                .role(model.getRole())
                .build();
    }


}



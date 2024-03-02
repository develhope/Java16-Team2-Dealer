package com.develhope.spring.features.user.model;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
                .role(requestDto.getRole())
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

    public static UserResponseDto convertModelToResponse(UserModel model) {
        return UserResponseDto.builder()
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

    public static List<UserModel> convertEntityListToModelList(List<UserEntity> entities) {
        return entities
                .stream()
                .map(UserModel::convertEntityToModel)
                .toList();
    }

    public static List<UserResponseDto> convertModelListToResponseList(List<UserModel> models) {
        return models
                .stream()
                .map(UserModel::convertModelToResponse)
                .toList();
    }


}



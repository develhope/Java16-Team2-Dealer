package com.develhope.spring.features.user.dto;

import com.develhope.spring.features.user.entities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean active;
    private Role role;
}

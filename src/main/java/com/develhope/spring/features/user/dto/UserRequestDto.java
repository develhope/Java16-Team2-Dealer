package com.develhope.spring.features.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean active;
}

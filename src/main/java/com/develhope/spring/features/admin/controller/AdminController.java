package com.develhope.spring.features.admin.controller;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>>  getAlladmins() {
        return ResponseEntity.ok(userService.getAllByRole(Role.ADMIN));
    }

    @PostMapping("admin")
    public ResponseEntity<UserResponseDto> createAdmin(@RequestBody UserRequestDto dto,
                                                       Role role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dto, Role.ADMIN));
    }


}

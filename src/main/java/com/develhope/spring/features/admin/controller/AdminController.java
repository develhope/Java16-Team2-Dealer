package com.develhope.spring.features.admin.controller;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @Operation(summary = "Returns all admins")
    @ApiResponse(responseCode = "200", description = "admins list",
                 content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})
    @GetMapping
    public ResponseEntity<List<UserResponseDto>>  getAlladmins() {
        return ResponseEntity.ok(userService.getAllByRole(Role.ADMIN));
    }

    @GetMapping("/admin")
    public ResponseEntity<UserResponseDto> getSelf(@AuthenticationPrincipal UserEntity user) {
        UserModel model = UserModel.convertEntityToModel(user);
        UserResponseDto dto = UserModel.convertModelToResponse(model);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/admin")
    public ResponseEntity<UserResponseDto> createAdmin(@RequestBody UserRequestDto dto,
                                                       Role role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dto, Role.ADMIN));
    }


}

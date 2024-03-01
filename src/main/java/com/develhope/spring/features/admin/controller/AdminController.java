package com.develhope.spring.features.admin.controller;

import com.develhope.spring.features.user.dto.UserErrorDto;
import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
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

    @Operation(summary = "Returns data about all admins")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "admins list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllByRole(Role.ADMIN));
    }

    //TRASFERIRE NEI RELATIVI CONTROLLER
//    @Operation(summary = "Returns data about all sellers")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "sellers list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
//    @GetMapping
//    public ResponseEntity<List<UserResponseDto>> getAllSellers() {
//        return ResponseEntity.ok(userService.getAllByRole(Role.SELLER));
//    }
//
//    @Operation(summary = "Returns data about all customers")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "customers list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
//    @GetMapping
//    public ResponseEntity<List<UserResponseDto>> getAllCustomers() {
//        return ResponseEntity.ok(userService.getAllByRole(Role.CUSTOMER));
//    }


    @Operation(summary = "Returns data about the logged admin")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "admins data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
    @GetMapping("/admin")
    public ResponseEntity<UserResponseDto> getSelf(@AuthenticationPrincipal UserEntity user) {
        UserModel model = UserModel.convertEntityToModel(user);
        UserResponseDto dto = UserModel.convertModelToResponse(model);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Creates a new admin with the required data")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Creates a new admin with the required data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
    @PostMapping("/admin")
    public ResponseEntity<UserResponseDto> createAdmin(@RequestBody UserRequestDto dto, Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto, Role.ADMIN));
    }




    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(
            @Parameter(description = "id of the admin to be deleted")
            @PathVariable("adminId") Long userToDeleteId,
            @AuthenticationPrincipal UserEntity userLogged) {

        Either<UserErrorDto,Boolean> result = userService.deleteUser(userToDeleteId, userLogged);
        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted user");
        }
    }


}

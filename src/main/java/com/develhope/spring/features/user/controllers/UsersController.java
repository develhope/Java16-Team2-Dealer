package com.develhope.spring.features.user.controllers;

import com.develhope.spring.features.user.dto.RoleDto;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;


    //TESTATO
    @Operation(summary = "Returns data about all users by role")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "users by role list", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))}),
                            @ApiResponse(responseCode = "403", description = "You don't have the permission to get these users")})
    @GetMapping("/byRole")
    public ResponseEntity<?> getAllUsersByRole(@AuthenticationPrincipal UserEntity userLogged, @RequestBody RoleDto role) {
        Either<UserErrorDto, List<UserResponseDto>> result = userService.getAllByRole(userLogged, role);

        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    //TESTATO
    @Operation(summary = "Returns data about the logged user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "user data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))})})
    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getSelf(@AuthenticationPrincipal UserEntity user) {
        UserModel model = UserModel.convertEntityToModel(user);
        UserResponseDto dto = UserModel.convertModelToResponse(model);
        return ResponseEntity.ok(dto);
    }

    //TESTATO
    @Operation(summary = "Returns data about the specified user by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "user data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))}),
                             @ApiResponse(responseCode = "403", description = "You don't have the permission to get these users"),
                            @ApiResponse(responseCode = "404", description = "User searched not found")})
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@AuthenticationPrincipal UserEntity userLogged,
                                                       @Parameter(description = "id of the user to be searched")
                                                       @PathVariable("id") Long id) {

        Either<UserErrorDto, UserResponseDto> result = userService.getUserById(id, userLogged);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
    }

    //TESTATO
    @Operation(summary = "Creates a new user with the required data")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Creates a new user with the required data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))}),
                            @ApiResponse(responseCode = "403", description = "You don't have the permission to create this user")})
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto dto, @AuthenticationPrincipal UserEntity userLogged) {

        Either<UserErrorDto, UserResponseDto> result = userService.createUser(dto, userLogged);
        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
        }

    }




//TODO IMPLEMENTARE IN MANIERA GENERICA CON CONTROLLO GERARCHIA

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteAdmin(
            @Parameter(description = "id of the user to be deleted")
            @PathVariable("id") Long userToDeleteId,
            @AuthenticationPrincipal UserEntity userLogged) {

        Either<UserErrorDto,Boolean> result = userService.deleteUser(userToDeleteId, userLogged);
        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted user");
        }
    }


}

package com.develhope.spring.features.seller;


import com.develhope.spring.features.user.dto.UserErrorDto;
import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllSellers() {
        return ResponseEntity.ok(userService.getAllByRole(Role.SELLER));
    }

    @GetMapping("/seller")
    public ResponseEntity<UserResponseDto> getSeller(@AuthenticationPrincipal UserEntity user) {
        UserModel model = UserModel.convertEntityToModel(user);
        UserResponseDto dto = UserModel.convertModelToResponse(model);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/seller/{id}")
    public ResponseEntity<?> deleteSeller(
            @Parameter(description = "id of the seller to be deleted")
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

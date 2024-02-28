package com.develhope.spring.features.seller;


import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

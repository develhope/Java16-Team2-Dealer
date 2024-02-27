package com.develhope.spring.features.seller;


import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

}

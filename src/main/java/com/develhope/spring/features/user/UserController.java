package com.develhope.spring.features.user;

import com.develhope.spring.features.user.dto.UserRequestDto;
import com.develhope.spring.features.user.dto.UserResponseDTO;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/admin")
    public ResponseEntity<UserResponseDTO> createAdmin(@AuthenticationPrincipal UserEntity user,
                                                       @RequestBody UserRequestDto newAdmin) {
        if ( user.getRole().equals(Role.ADMIN) && user.isActive()) {
           return ResponseEntity.ok(userService.createAdmin(newAdmin));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}

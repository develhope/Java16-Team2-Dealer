package com.develhope.spring.features.authentication.controllers;

import com.develhope.spring.features.authentication.AuthenticationService;
import com.develhope.spring.features.authentication.dto.request.SignUpRequest;
import com.develhope.spring.features.authentication.dto.request.SigninRequest;
import com.develhope.spring.features.authentication.dto.response.JwtAuthenticationResponse;
import com.develhope.spring.features.user.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }


}
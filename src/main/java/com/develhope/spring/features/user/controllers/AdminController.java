package com.develhope.spring.features.user.controllers;

import com.develhope.spring.features.user.UserService;
import com.develhope.spring.features.user.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

}

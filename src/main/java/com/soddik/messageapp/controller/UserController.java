package com.soddik.messageapp.controller;

import com.soddik.messageapp.dto.UserRequest;
import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest request) {
        try {
            return new ResponseEntity<>(appUserService.create(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User with name: " + request.name() + " already exist or wrong format");
        }

    }

    @PostMapping("/")
    public List<UserResponse> getAllUsers() {
        return appUserService.findAll();
    }
}

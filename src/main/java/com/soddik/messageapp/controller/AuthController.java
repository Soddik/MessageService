package com.soddik.messageapp.controller;

import com.soddik.messageapp.dto.JwtResponse;
import com.soddik.messageapp.dto.UserRequest;
import com.soddik.messageapp.model.AppUser;
import com.soddik.messageapp.security.JwtProvider;
import com.soddik.messageapp.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthUserService authUserService;
    private final JwtProvider jwtProvider;

    public AuthController(AuthenticationManager authenticationManager, AuthUserService authUserService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.authUserService = authUserService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserRequest request) {
        try {
            //all request fields != null
            if (request.name() == null || request.password() == null){
                return ResponseEntity.badRequest().body("Wrong format: fields cannot be null.");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.name(), request.password()));
            AppUser user = authUserService.findByName(request.name());
            String token = jwtProvider.createToken(user.getName());

            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid name/password", HttpStatus.FORBIDDEN);
        }
    }
}

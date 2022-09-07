package com.soddik.messageapp.service;

import com.soddik.messageapp.dto.UserRequest;
import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.mapper.UserMapper;
import com.soddik.messageapp.model.AppUser;
import com.soddik.messageapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;


    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(UserRequest userRequest) {
        AppUser user = new AppUser(userRequest.name(), passwordEncoder.encode(userRequest.password()));
        return UserMapper.toDto(appUserRepository.save(user));
    }

    public List<UserResponse> findAll() {
        return appUserRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }
}

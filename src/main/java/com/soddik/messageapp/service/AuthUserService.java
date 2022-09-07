package com.soddik.messageapp.service;

import com.soddik.messageapp.model.AppUser;
import com.soddik.messageapp.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public AuthUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser findByName(String name) {
        return appUserRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("User doesnt exists."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return new User(user.getName(), user.getPassword(), new ArrayList<>());
    }
}

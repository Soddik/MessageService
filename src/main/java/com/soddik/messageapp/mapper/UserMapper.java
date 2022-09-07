package com.soddik.messageapp.mapper;

import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.model.AppUser;

public class UserMapper {
    private UserMapper() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }

    public static UserResponse toDto(AppUser appUser) {
        return new UserResponse(appUser.getId(), appUser.getName());
    }
}

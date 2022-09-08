package com.soddik.messageapp.mapper;

import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.model.AppUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toDto() {
        AppUser user = new AppUser(1L, "user_from_db", "pswd");
        UserResponse response = UserMapper.toDto(user);

        assertEquals(user.getId(), response.id());
        assertEquals(user.getName(), response.name());
    }
}
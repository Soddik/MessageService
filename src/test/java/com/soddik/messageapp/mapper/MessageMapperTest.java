package com.soddik.messageapp.mapper;

import com.soddik.messageapp.dto.MessageResponse;
import com.soddik.messageapp.dto.UserRequest;
import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.model.AppUser;
import com.soddik.messageapp.model.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageMapperTest {

    @Test
    void toDto() {
        AppUser user = new AppUser(1L, "user_from_db", "pswd");
        Message message = new Message(1L, "test_content", user);
        MessageResponse response = MessageMapper.toDto(message);

        assertEquals(message.getOwner().getName(), response.name());
        assertEquals(message.getContent(), response.content());
    }
}
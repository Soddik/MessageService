package com.soddik.messageapp.mapper;

import com.soddik.messageapp.dto.MessageResponse;
import com.soddik.messageapp.model.Message;

public class MessageMapper {
    private MessageMapper() {
        throw new AssertionError(String.format("Class %s cannot be instantiated", this.getClass().getSimpleName()));
    }

    public static MessageResponse toDto(Message message) {
        return new MessageResponse(message.getOwner().getName(), message.getContent());
    }
}

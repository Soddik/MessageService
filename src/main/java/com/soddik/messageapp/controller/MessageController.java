package com.soddik.messageapp.controller;

import com.soddik.messageapp.dto.MessageRequest;
import com.soddik.messageapp.dto.MessageResponse;
import com.soddik.messageapp.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity<?> registerMessage(@RequestBody MessageRequest messageRequest) {
        return checkMessageContent(messageRequest);
    }

    private ResponseEntity<?> checkMessageContent(MessageRequest messageRequest) {
        if (messageRequest.content().contains("history")) {
            Integer msgLimit = Integer.valueOf(messageRequest.content().split(" ")[1]);
            List<MessageResponse> messageResponseList = messageService.getLastXMessages(msgLimit);
            return messageResponseList.size() > 0 ? ResponseEntity.ok(messageResponseList) : ResponseEntity.noContent().build();
        } else {
            return messageService.save(messageRequest) ? new ResponseEntity<>(HttpStatus.CREATED) : ResponseEntity.internalServerError().build();
        }
    }
}

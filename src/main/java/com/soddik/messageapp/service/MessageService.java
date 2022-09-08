package com.soddik.messageapp.service;

import com.soddik.messageapp.dto.MessageRequest;
import com.soddik.messageapp.dto.MessageResponse;
import com.soddik.messageapp.mapper.MessageMapper;
import com.soddik.messageapp.model.Message;
import com.soddik.messageapp.repository.AppUserRepository;
import com.soddik.messageapp.repository.MessageRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    // TODO: 07.09.2022 bad practice 
    private final AppUserRepository appUserRepository;

    public MessageService(MessageRepository messageRepository, AppUserRepository appUserRepository) {
        this.messageRepository = messageRepository;
        this.appUserRepository = appUserRepository;
    }


    public Boolean save(MessageRequest messageRequest) {
        Message message = new Message();
        message.setContent(messageRequest.content());
        message.setOwner(appUserRepository.findByName(messageRequest.name()).orElseThrow(() -> new UsernameNotFoundException("User doesnt exists.")));
        return messageRepository.save(message).getId() != null;
    }

    public List<MessageResponse> getLastXMessages(Integer msgLimit) {
        return messageRepository.findLastXMessages(msgLimit).stream()
                .map(MessageMapper::toDto)
                .toList();
    }
}

package com.soddik.messageapp.controller;

import com.soddik.messageapp.dto.MessageRequest;
import com.soddik.messageapp.dto.MessageResponse;
import com.soddik.messageapp.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.context.request.RequestContextHolder.setRequestAttributes;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {
    @InjectMocks
    private MessageController controller;

    @Mock
    private MessageService service;

    @Test
    void saveMessage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        setRequestAttributes(new ServletRequestAttributes(request));
        when(service.save(any(MessageRequest.class))).thenReturn(true);

        MessageRequest messageRequest = new MessageRequest("test_user_1", "test_msg");
        ResponseEntity<?> responseEntity = controller.retrieveMessage(messageRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void testGetLastXMessages() {
        int msgLimit = 3;
        List<MessageResponse> userResponseList = generateMessageResponseList(msgLimit);

        MockHttpServletRequest request = new MockHttpServletRequest();
        setRequestAttributes(new ServletRequestAttributes(request));
        when(service.getLastXMessages(any(Integer.class))).thenReturn(userResponseList);

        MessageRequest messageRequest = new MessageRequest("test_user_1", "history 3");
        ResponseEntity<?> responseEntity = controller.retrieveMessage(messageRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((List<MessageResponse>) Objects.requireNonNull(responseEntity.getBody())).size()).isEqualTo(3);
    }

    private List<MessageResponse> generateMessageResponseList(int msgLimit) {
        List<MessageResponse> list = new ArrayList<>();
        for (int index = 1; index < msgLimit + 1; index++) {
            list.add(new MessageResponse("test_user_" + index, "test_user_" + index + "_msg"));
        }
        return list;
    }
}
package com.soddik.messageapp.controller;

import com.soddik.messageapp.dto.UserRequest;
import com.soddik.messageapp.dto.UserResponse;
import com.soddik.messageapp.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.context.request.RequestContextHolder.setRequestAttributes;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private AppUserService service;

    @Test
    void givenUserWhenRegisterUserThanReturnsCreated() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        setRequestAttributes(new ServletRequestAttributes(request));
        when(service.create(any(UserRequest.class))).thenReturn(new UserResponse(1L, "test_user_1"));

        UserRequest user_1 = new UserRequest("test_user_1", "password");
        ResponseEntity<?> responseEntity = controller.register(user_1);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = ((UserResponse) Objects.requireNonNull(responseEntity.getBody())).id();
        String name = ((UserResponse) responseEntity.getBody()).name();
        assertThat((id == 1 && name.equals("test_user_1"))).isEqualTo(true);
    }

    @Test
    void whenGetAllUsersThanReturnOkAndAllUsers() {
        List<UserResponse> userResponseList = List.of(
                new UserResponse(1L, "test_user_1"),
                new UserResponse(2L, "test_user_2")
        );

        when(service.findAll()).thenReturn(userResponseList);

        List<UserResponse> result = controller.getAllUsers();

        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).id())
                .isEqualTo(1L);
        assertThat(result.get(0).name())
                .isEqualTo("test_user_1");

        assertThat(result.get(1).id())
                .isEqualTo(2L);
        assertThat(result.get(1).name())
                .isEqualTo("test_user_2");
    }
}
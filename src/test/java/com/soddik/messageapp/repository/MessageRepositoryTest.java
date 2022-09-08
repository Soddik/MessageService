package com.soddik.messageapp.repository;

import com.soddik.messageapp.model.AppUser;
import com.soddik.messageapp.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MessageRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void givenMessageWhenSaveThanThrowsUsernameNotFoundException() {
        Message message = new Message();
        message.setContent("content");

        assertThrows(UsernameNotFoundException.class,
                () -> message.setOwner(appUserRepository.findByName("test")
                        .orElseThrow(() -> new UsernameNotFoundException("User doesnt exists.")))
        );
    }

    @Test
    void findLastXMessages() {
        entityManager.persist(new AppUser("test_user", "password"));
        generateMessages(10);
        List<Message> messageList = messageRepository.findLastXMessages(5);
        assertThat(messageList.size()).isEqualTo(5);
        messageList.forEach(message -> assertThat(message.getOwner().getName()).isEqualTo("test_user"));
    }

    private void generateMessages(int msgLimit) {
        AppUser user = appUserRepository.findByName("test_user").get();
        for (int index = 0; index < msgLimit; index++) {
            entityManager.persist(new Message("test_msg_" + index, user));
        }
    }
}
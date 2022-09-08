package com.soddik.messageapp.repository;

import com.soddik.messageapp.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AppUserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository repository;


    @Test
    void findByName() {
        entityManager.persist(new AppUser("test_user", "password"));
        AppUser user = repository.findByName("test_user").get();
        assertThat(user.getName()).isEqualTo("test_user");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getId()).isNotEqualTo(null);
    }
}
package com.programmers.service.user;

import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.repository.user.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByNickName() {
    }

    @Test
    void update() {
    }


    @Test
    void deleteById() {
    }
}
package com.programmers.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.repository.user.UserRepository;
import com.programmers.service.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
//@WebMvcTest
class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void clean() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        userRepository.deleteAll();

    }

    @Test
    void saveUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        User user = basicUserData().toEntity();
        User savedUser = userRepository.save(user);

        String json = objectMapper.writeValueAsString(savedUser);

        //when,then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void searchUserById() throws Exception {
        User user = basicUserData().toEntity();
        User savedUser = userRepository.save(user);

        //when,then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", savedUser.getUserId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").value(String.valueOf(savedUser.getUserId())))
                .andExpect(jsonPath("$.userName").value(savedUser.getUserName()));
    }

    @Test
    void searchUserByNickName() throws Exception {
        User user = basicUserData().toEntity();
        UserRequestDto userRequestDto = UserRequestDto.of(user);
        User savedUser = userService.save(userRequestDto);

        //when,then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/search/nickname/{nickName}", savedUser.getNickName()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").value(String.valueOf(savedUser.getUserId())))
                .andExpect(jsonPath("$.userName").value(savedUser.getUserName()));
    }

    @Test
    void searchUserByPhoneNumber() throws Exception {
        User user = basicUserData().toEntity();
        User savedUser = userRepository.save(user);

        //when,then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/search/phoneNumber/{phoneNumber}", savedUser.getPhoneNumber()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").value(String.valueOf(savedUser.getUserId())))
                .andExpect(jsonPath("$.userName").value(savedUser.getUserName()));
    }

    @Test
    void updateUser() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        User user = basicUserData().toEntity();
        User savedUser = userRepository.save(user);

        Long updateUserId = savedUser.getUserId();

        User updatedUser = User.builder()
                .userName(basicUserData().getUserName())
                .password(basicUserData().getPassword())
                .nickName(basicUserData().getNickName())
                .phoneNumber(basicUserData().getPhoneNumber())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}",updateUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void deleteUserId() throws Exception {
        //given
        User user = basicUserData().toEntity();
        User savedUser = userRepository.save(user);

        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", savedUser.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        assertEquals(0L, userRepository.count());

    }

    private UserRequestDto basicUserData() {
        return UserRequestDto.builder()
                .userName("chacha")
                .nickName("chacha")
                .password("testtest")
                .phoneNumber("11111111111")
                .grade(Grade.NORMAL)
                .role(Role.CUSTOMER)
                .build();
    }

    private UserRequestDto dummyUserData() {
        return UserRequestDto.builder()
                .userName("mandu")
                .nickName("mandu")
                .password("testtesttest")
                .phoneNumber("22222222222")
                .build();
    }
}

package com.programmers.dto.user;

import com.programmers.domain.user.User;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;

public class UserRequestDto {
    @NotNull
    private final Long userId;
    @NotBlank
    @Length(min = 1, max = 30)
    private final String name;
    @NotBlank
    @Length(min = 1, max = 30)
    private final String password;
    @NotBlank
    @Length(min = 1, max = 30)
    private final String nickName;
    @NotBlank
    @Length(min = 1, max = 30)
    private final String phoneNumber;

    @Builder
    public UserRequestDto(Long userId, String name, String password, String nickName, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .name(name)
                .password(password)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .build();
    }
}

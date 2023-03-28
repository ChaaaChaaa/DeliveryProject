package com.programmers.dto.login;

import com.programmers.domain.User;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LoginFormRequestDto {

    @NotBlank
    private final String userId;

    @NotBlank
    private final String password;

    @Builder
    public LoginFormRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}

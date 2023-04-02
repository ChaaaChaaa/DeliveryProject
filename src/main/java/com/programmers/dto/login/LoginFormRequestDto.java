package com.programmers.dto.login;

import com.programmers.domain.user.User;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LoginFormRequestDto {

    @NotBlank
    private final String nickName;

    @NotBlank
    private final String password;

    @Builder
    public LoginFormRequestDto(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .nickName(nickName)
                .password(password)
                .build();
    }
}

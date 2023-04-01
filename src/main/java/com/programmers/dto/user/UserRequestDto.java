package com.programmers.dto.user;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;

public class UserRequestDto {
    @NotNull
    private Long userId;
    @NotBlank
    @Length(min = 1, max = 30)
    private String name;
    @NotBlank
    @Length(min = 1, max = 30)
    private String password;
    @NotBlank
    @Length(min = 1, max = 30)
    private String nickName;
    private String grade;
    @NotBlank
    @Length(min = 1, max = 30)
    private int phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserRequestDto(Long userId, String name, String password, String nickName, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
}

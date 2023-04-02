package com.programmers.dto.user;

import com.programmers.domain.user.User;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserResponseDto {
    private Long userId;
    private String name;
    private String password;
    private String nickName;
    private String grade;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserResponseDto(Long userId, String name, String password, String nickName, String grade, String phoneNumber, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}

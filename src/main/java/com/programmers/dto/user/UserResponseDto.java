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
    private String userName;
    private String password;
    private String nickName;
    private String grade;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserResponseDto(Long userId, String userName, String password, String nickName, Grade grade, String phoneNumber, Role role, LocalDateTime createdAt) {
        this.userId = userId;
        this.userName = userName;
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

package com.programmers.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String userName;
    private String password;
    private String nickName;
    private Grade grade;
    private String phoneNumber;
    private Role role;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime createdAt;

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
    }

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .grade(user.getGrade())
                .role(user.getRole())
                .build();
    }
}

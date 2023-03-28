package com.programmers.dto.user;

import com.programmers.domain.User;
import com.programmers.dto.menu.MenuResponseDto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserResponseDto {
    private String userId;
    private String name;
    private String password;
    private String nickName;
    private String grade;
    private int phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserResponseDto(String userId, String name, String password, String nickName, String grade, int phoneNumber, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
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


    //role과 grade는 빼고 만들수있는가?
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

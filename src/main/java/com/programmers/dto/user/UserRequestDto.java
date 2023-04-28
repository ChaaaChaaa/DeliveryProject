package com.programmers.dto.user;

import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    @NotNull
    private Long userId;
    @NotBlank
    @Length(min = 1, max = 30)
    private String userName;
    @NotBlank
    @Length(min = 1, max = 30)
    private String password;
    @NotBlank
    @Length(min = 1, max = 30)
    private String nickName;
    @NotBlank
    @Length(min = 1, max = 30)
    private String phoneNumber;

    private Role role;
    private LocalDateTime createdAt;
    private Grade grade;



    @Builder
    public UserRequestDto(Long userId, String userName, String password, String nickName, String phoneNumber, Role role, LocalDateTime createdAt, Grade grade) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.grade = grade;
    }


    public static UserRequestDto of(User user) {
        return UserRequestDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .grade(user.getGrade())
                .build();
    }


    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .role(role)
                .createdAt(createdAt)
                .grade(grade)
                .build();
    }
}

package com.programmers.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Column(nullable = false, unique = true)
    private String nickName;
    private String grade;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Column(nullable = false, unique = true)
    private int phoneNumber;
    private String role;


    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public User(String userId, String name, String password, String nickName, int phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }
}

package com.programmers.domain.user;

import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userName","nickName"})})
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @Column(name = "userName", nullable = false)
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "이름은 영문으로만 입력 가능합니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @NotNull
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @NotNull
    @Column(nullable = false)
    private String nickName;

    @Column(columnDefinition = "ENUM('NORMAL', 'SILVER', 'GOLD')")
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "ENUM('CUSTOMER', 'STORE')")
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    private LocalDateTime createdAt;


    @Builder
    public User(String userName, String password, String nickName, Grade grade, String phoneNumber, Role role) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }


    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

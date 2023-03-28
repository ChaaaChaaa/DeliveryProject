package com.programmers.dto.login;

import com.programmers.domain.User;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpFormRequestDto {
    @NotBlank
    @Length(min = 5, max = 20)
    private final String userId;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 8자 이상 50자 이하로 입력해주세요.")
    private String password;
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickName;
    private String grade;

    @NotBlank
    @Size(min = 10, max = 11, message = "핸드폰 번호 숫자가 맞지 않습니다.")
    private int phoneNumber;
    private String role;

    @Builder
    public SignUpFormRequestDto(String userId,String name, String password, String nickName,int phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .nickName(nickName)
                .build();
    }
}

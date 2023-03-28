package com.programmers.dto.token;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponseDto {
    private String accessToken;
    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}

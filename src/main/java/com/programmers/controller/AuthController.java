package com.programmers.controller;


import com.programmers.dto.login.LoginFormRequestDto;
import com.programmers.dto.login.SignUpFormRequestDto;
import com.programmers.dto.token.TokenResponseDto;
import com.programmers.service.auth.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody @Valid SignUpFormRequestDto signUpFormRequestDto) {
        TokenResponseDto tokenResponseDto = authService.signUp(signUpFormRequestDto);
        return ResponseEntity.ok().body(tokenResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginFormRequestDto loginFormRequestDto) {
        TokenResponseDto tokenResponseDto = authService.login(loginFormRequestDto);
        return ResponseEntity.ok().body(tokenResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse httpServletResponse) {
        authService.invalidateToken(httpServletResponse);
        return ResponseEntity.ok().build();
    }
}

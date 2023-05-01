package com.programmers.controller.auth;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.dto.login.LoginFormRequestDto;
import com.programmers.dto.login.SignUpFormRequestDto;
import com.programmers.dto.token.TokenResponseDto;
import com.programmers.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
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

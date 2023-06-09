package com.programmers.service.auth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmers.domain.user.User;
import com.programmers.dto.login.LoginFormRequestDto;
import com.programmers.dto.login.SignUpFormRequestDto;
import com.programmers.dto.token.TokenResponseDto;
import com.programmers.exception.security.LoginFailedException;
import com.programmers.exception.user.DuplicateNickNameException;
import com.programmers.repository.user.UserRepository;
import com.programmers.security.JwtTokenProvider;
import com.programmers.security.PasswordEncoder;
import com.programmers.session.SessionConstants;
import com.programmers.util.CookieUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public TokenResponseDto signUp(SignUpFormRequestDto signUpFormRequestDto) {
		String nickName = signUpFormRequestDto.getNickName();

		if (userRepository.findByNickName(nickName).isPresent()) {
			throw new DuplicateNickNameException();
		}

		User user = userRepository.save(signUpFormRequestDto.toEntity());
		return generateToken(user);
	}

	private TokenResponseDto generateToken(User user) {
		String accessToken = jwtTokenProvider.createToken(user);
		return new TokenResponseDto(accessToken);
	}

	public TokenResponseDto login(LoginFormRequestDto loginFormRequestDto) {
		User user = userRepository.findByNickName(loginFormRequestDto.getNickName())
			.orElseThrow(LoginFailedException::new);

		if (!passwordEncoder.matches(loginFormRequestDto.getPassword(), user.getPassword())) {
			throw new LoginFailedException();
		}

		return generateToken(user);
	}

	public void invalidateToken(HttpServletResponse httpServletResponse) {
		CookieUtil.deleteCookie(httpServletResponse, SessionConstants.ACCESS_TOKEN);
	}
}

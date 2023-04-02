package com.programmers.service.auth;

import com.programmers.domain.User;
import com.programmers.dto.login.LoginFormRequestDto;
import com.programmers.dto.login.SignUpFormRequestDto;
import com.programmers.exception.DuplicateNickNameException;
import com.programmers.exception.LoginFailedException;
import com.programmers.repository.user.UserRepository;
import com.programmers.dto.token.TokenResponseDto;
import com.programmers.security.JwtTokenProvider;
import com.programmers.security.PasswordEncoder;

import com.programmers.session.SessionConstants;
import com.programmers.util.CookieUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

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

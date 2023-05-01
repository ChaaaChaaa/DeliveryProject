package com.programmers.security;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.domain.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SpringBootTest
class JwtTokenProviderTest {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@BeforeEach
	void setUp() {
		jwtTokenProvider.secretKey = "chacha";
		jwtTokenProvider.tokenValidityInSeconds = 3600;
	}

	@Test
	void createToken() {
		User user = basicUserData();
		user.setUserName("니노");
		user.changePhoneNumber("01033333333");

		String token = jwtTokenProvider.createToken(user);

		assertNotNull(token);

		Claims claims = Jwts.parser().setSigningKey(jwtTokenProvider.secretKey).parseClaimsJws(token).getBody();
		assertEquals(user.getNickName(), claims.getSubject());
		assertEquals(user.getRole(), claims.get("auth"));
		assertTrue(claims.getIssuedAt().before(new Date()));
		assertTrue(claims.getExpiration().after(new Date()));
	}

	private User basicUserData() {
		return User.builder()
			.userName("chacha")
			.password("123456")
			.nickName("차차")
			.phoneNumber("01011111111")
			.build();
	}
}

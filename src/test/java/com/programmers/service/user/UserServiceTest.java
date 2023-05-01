package com.programmers.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.programmers.domain.user.Grade;
import com.programmers.domain.user.Role;
import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.repository.user.UserRepository;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@BeforeEach
	void clean() {
		userRepository.deleteAll();
	}

	@Test
	void save() {
		User user = basicUserData().toEntity();
		User savedUser = userRepository.save(user);
		assertEquals(user.getUserName(), savedUser.getUserName());
		assertEquals(user.getGrade(), savedUser.getGrade());
	}

	@Test
	void findByUserId() {
		//given
		User user = basicUserData().toEntity();
		User savedUser = userService.save(basicUserData());

		//when
		UserResponseDto userResponseDto = userService.findByUserId(savedUser.getUserId());

		//then
		assertNotNull(userResponseDto);
		assertEquals("chacha", userResponseDto.getUserName());
		assertEquals("chacha", userResponseDto.getNickName());
		assertEquals("11111111111", userResponseDto.getPhoneNumber());
		assertEquals(user.getGrade(), userResponseDto.getGrade()); //물어보기
		assertEquals(user.getRole(), userResponseDto.getRole());
	}

	@Test
	void findByNickName() {
		//given
		User user = basicUserData().toEntity();
		User savedUser = userRepository.save(user);

		//when
		UserResponseDto userResponseDto = userService.findByNickName(savedUser.getNickName());

		//then
		assertNotNull(userResponseDto);
		assertEquals("chacha", userResponseDto.getUserName());
		assertEquals("chacha", userResponseDto.getNickName());
		assertEquals("11111111111", userResponseDto.getPhoneNumber());
		assertEquals(Grade.NORMAL, userResponseDto.getGrade());
		assertEquals(Role.CUSTOMER, userResponseDto.getRole());
	}

	@Test
	void update() {
		//given
		User user = basicUserData().toEntity();
		UserRequestDto userRequestDto = UserRequestDto.of(user);
		User savedUser = userService.save(userRequestDto);
		//when
		userService.update(savedUser.getUserId(), dummyUserData());

		//then
		User targetUser = userRepository.findByUserId(savedUser.getUserId()).orElseThrow();
		assertEquals(dummyUserData().getUserName(), targetUser.getUserName());
		assertEquals(dummyUserData().getNickName(), targetUser.getNickName());
		assertEquals(dummyUserData().getPhoneNumber(), targetUser.getPhoneNumber());
	}

	@Test
	void deleteById() {
		User user = basicUserData().toEntity();
		User savedUser = userRepository.save(user);

		//when
		userRepository.delete(savedUser);

		//then
		Optional<User> userId = userRepository.findByUserId(savedUser.getUserId());
		Assertions.assertTrue(userId.isEmpty());
	}

	private UserRequestDto basicUserData() {
		return UserRequestDto.builder()
			.userName("chacha")
			.nickName("chacha")
			.password("testtest")
			.phoneNumber("11111111111")
			.grade(Grade.NORMAL)
			.role(Role.CUSTOMER)
			.build();
	}

	private UserRequestDto dummyUserData() {
		return UserRequestDto.builder()
			.userName("mandu")
			.nickName("mandu")
			.password("testtesttest")
			.phoneNumber("22222222222")
			.build();
	}
}

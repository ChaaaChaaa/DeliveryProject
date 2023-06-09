package com.programmers.service.user;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.exception.user.DuplicateNickNameException;
import com.programmers.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public User save(UserRequestDto userRequestDto) {
		return userRepository.save(userRequestDto.toEntity());
	}

	public UserResponseDto findByUserId(Long userId) {
		return UserResponseDto.of(userRepository.findByUserId(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 회원이 존재하지 않습니다.")));
	}

	public UserResponseDto findByNickName(String userNickName) {
		return UserResponseDto.of(userRepository.findByNickName(userNickName)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 닉네임의 회원이 존재하지 않습니다.")));
	}

	public UserResponseDto findByPhoneNumber(String phoneNumber) {
		return UserResponseDto.of(userRepository.findByPhoneNumber(phoneNumber)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 번호의 회원이 존재하지 않습니다.")));
	}

	@Transactional
	public void update(long userId, UserRequestDto userRequestDto) {
		User updatedUser = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
		updatedUser.setUserId(userId);
		Optional<User> existingUser = userRepository.findByNickName(userRequestDto.getNickName());

		if (existingUser.isPresent() && !existingUser.get().equals(updatedUser)) {
			throw new DuplicateNickNameException();
		}

		updatedUser.changeUserName(userRequestDto.getUserName());
		updatedUser.changeNickName(userRequestDto.getNickName());
		updatedUser.changePassword(userRequestDto.getPassword());
		updatedUser.changePhoneNumber(userRequestDto.getPhoneNumber());
		userRepository.save(updatedUser);
	}

	public void deleteById(long userId) {
		userRepository.deleteById(userId);
	}
}

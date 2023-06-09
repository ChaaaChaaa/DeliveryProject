package com.programmers.controller.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.dto.user.UserRequestDto;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/save")
	public void saveUser(@RequestBody UserRequestDto userRequestDto) {
		userService.save(userRequestDto);
	}

	@GetMapping("/{userId}")
	public UserResponseDto searchUserById(@PathVariable Long userId) {
		return userService.findByUserId(userId);
	}

	@GetMapping("/search/nickname/{nickName}")
	public UserResponseDto searchUserByNickName(@PathVariable String nickName) {
		return userService.findByNickName(nickName);
	}

	@GetMapping("/search/phoneNumber/{phoneNumber}")
	public UserResponseDto searchUserByPhoneNumber(@PathVariable String phoneNumber) {
		return userService.findByPhoneNumber(phoneNumber);
	}

	@PutMapping("/{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto) {
		userService.update(userId, userRequestDto);
	}

	@DeleteMapping("/{userId}")
	public void deleteUserId(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
}

package com.programmers.controller.user;

import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public void saveUser(@RequestBody UserRequestDto userRequestDto) {
        userService.save(userRequestDto);
    }

    @GetMapping("/{userId}")
    public UserResponseDto searchUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/search/nickname")
    public UserResponseDto searchUserByNickName(@RequestParam("nickName") String nickName) {
        return userService.findByNickName(nickName);
    }

    @GetMapping("/search/findByPhoneNumber")
    public UserResponseDto searchUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserResponseDto userResponseDto) {
        userService.update(userId, userResponseDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserId(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}

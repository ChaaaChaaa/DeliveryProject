package com.programmers.service.user;

import com.programmers.domain.User;
import com.programmers.dto.user.UserResponseDto;
import com.programmers.repository.user.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 회원이 존재하지 않습니다."));
    }

    public User findByNickName(String userNickName) {
        return userRepository.findByNickName(userNickName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 닉네임의 회원이 존재하지 않습니다."));
    }

    @Transactional
    public void update(long userId, User user) {
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        updatedUser.changeName(user.getName());
        updatedUser.changeNickName(user.getNickName());
        updatedUser.changePassword(user.getPassword());
        updatedUser.changePhoneNumber(user.getPhoneNumber());
        userRepository.save(updatedUser);
    }

    public UserResponseDto getUserResponse(User user){
        return UserResponseDto.of(user);
    }

    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }
}

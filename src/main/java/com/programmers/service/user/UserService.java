package com.programmers.service.user;

import com.programmers.domain.user.User;
import com.programmers.dto.user.UserRequestDto;
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

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 회원이 존재하지 않습니다."));
    }

    public User findByNickName(String userNickName) {
        return userRepository.findByNickName(userNickName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 닉네임의 회원이 존재하지 않습니다."));
    }

    @Transactional
    public void update(long userId, UserResponseDto userResponseDto) {
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Optional<User> existingUser = userRepository.findByNickName(userResponseDto.getNickName());

        if (existingUser.isPresent() && !existingUser.get().equals(updatedUser)) {
            throw new DuplicateNickNameException();
        }

        updatedUser.setName(userResponseDto.getName());
        updatedUser.setNickName(userResponseDto.getNickName());
        updatedUser.setPassword(userResponseDto.getPassword());
        updatedUser.setPhoneNumber(userResponseDto.getPhoneNumber());
        userRepository.save(updatedUser);
    }

    public UserResponseDto getUserResponse(User user){
        return UserResponseDto.of(user);
    }

    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }
}

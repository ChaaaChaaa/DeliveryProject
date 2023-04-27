package com.programmers.repository.user;

import com.programmers.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long userId);

    Optional<User> findByNickName(String NickName);

    Optional<User> findByUser(Long userId);

    Optional<User> findByPhoneNumber(String PhoneNumber);

    void delete(@Nullable User user);
}

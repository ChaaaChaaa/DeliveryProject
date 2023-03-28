package com.programmers.repository.user;

import com.programmers.domain.Food;
import com.programmers.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByNickName(String NickName);

    Optional<User> findByPhoneNumber(int PhoneNumber);

    void delete(@Nullable User user);
}
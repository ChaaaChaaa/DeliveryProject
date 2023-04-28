package com.programmers.repository.user;

import com.programmers.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long userId);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByPhoneNumber(String PhoneNumber);

    void delete(@Nullable User user);
}

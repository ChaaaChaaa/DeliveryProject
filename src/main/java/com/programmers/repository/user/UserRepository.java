package com.programmers.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.programmers.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNickName(String nickName);

	Optional<User> findByUserId(Long userId);

	Optional<User> findByPhoneNumber(String PhoneNumber);

	void delete(@Nullable User user);
}

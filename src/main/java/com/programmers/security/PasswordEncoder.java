package com.programmers.security;

public interface PasswordEncoder {
	boolean matches(CharSequence rawPassword, String encodedPassword);
}

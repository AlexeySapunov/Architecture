package ru.gb.patterns.proxy;

import org.springframework.security.core.userdetails.User;

public interface UserService {

    User findUserByUsername(String username);
}

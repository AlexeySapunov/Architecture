package ru.gb.patterns.proxy;

import org.springframework.security.core.userdetails.User;

public class ProxyUserAuthService implements UserService {

    private final UserAuthService authService;

    public ProxyUserAuthService(UserAuthService authService) {
        this.authService = authService;
    }

    @Override
    public User findUserByUsername(String username) {
        return authService.findUserByUsername(username);
    }
}

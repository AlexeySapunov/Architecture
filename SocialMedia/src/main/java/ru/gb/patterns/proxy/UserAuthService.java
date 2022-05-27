package ru.gb.patterns.proxy;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserAuthService implements UserService {

    private final UserRepo userRepo;

    public UserAuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

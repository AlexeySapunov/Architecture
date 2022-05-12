package ru.gb.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final Set<String> onlineUsers;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.onlineUsers = new ConcurrentSkipListSet<>();
    }

    public void create(UserFormType userFormType) {
        User user = new User();
        user.setUsername(userFormType.getUsername());
        user.setPassword(passwordEncoder.encode(userFormType.getPassword()));
        repository.save(user);
    }

    public List<UserType> findAllUsers() {
        return repository.findAll().stream()
                .map(UserType::new)
                .map(usr -> usr.withOnline(onlineUsers.contains(usr.getUsername())))
                .collect(Collectors.toList());
    }

    public UserType findByUsername(String username) {
        return repository.findUserByUsername(username)
                .map(UserType::new)
                .map(usr -> usr.withOnline(onlineUsers.contains(usr.getUsername())))
                .orElse(null);
    }

    public void setUserOnline(String username) {
        onlineUsers.add(username);
    }

    public void setUserOffline(String username) {
        onlineUsers.remove(username);
    }
}

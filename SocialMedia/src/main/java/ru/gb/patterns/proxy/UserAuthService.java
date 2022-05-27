package ru.gb.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserAuthService implements UserService {

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserAuthService(UserRepository userRepository, @Usage(Usage.ChoiceUsages.AUTH) UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<UserType> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public UserType findByUsername(String username) throws UsernameNotFoundException {
        User userDetail = userRepository.findUserByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return convertToDetail(userDetail);
    }

    private static UserType convertToDetail(UserDetails user) {
        return new UserType(
                new ru.gb.patterns.proxy.User(
                        user.getUsername(),
                        user.getPassword()));
    }
}

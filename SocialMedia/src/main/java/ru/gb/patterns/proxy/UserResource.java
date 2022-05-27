package ru.gb.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserResource implements UserService {
    
    private final UserService userService;

    @Autowired
    public UserResource(@Usage(Usage.ChoiceUsages.REST) UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/all", produces = "application/json")
    public List<UserType> findAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/current", produces = "application/json")
    public UserType findByUsername(String username) {
        return userService.findByUsername(username);
    }
}

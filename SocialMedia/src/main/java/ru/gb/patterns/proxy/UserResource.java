package ru.gb.patterns.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/all", produces = "application/json")
    public List<UserType> getAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/current", produces = "application/json")
    public UserType currentUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}

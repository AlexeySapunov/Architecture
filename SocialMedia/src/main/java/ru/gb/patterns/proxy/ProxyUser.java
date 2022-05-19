package ru.gb.patterns.proxy;

import org.springframework.context.annotation.Bean;

public class ProxyUser {

    public static void main(String[] args) {
        User user = new User();

        UserService userService = getUserService();
        userService.findAllUsers();
        userService.findByUsername(user.getUsername());
    }

    @Bean
    private static UserService getUserService() {
        return new UserResource(new UserServiceImpl());
    }
}

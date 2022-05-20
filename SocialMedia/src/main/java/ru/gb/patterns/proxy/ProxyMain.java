package ru.gb.patterns.proxy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProxyMain {

    private static UserRepo userRepo;

    public static void main(String[] args) {

        UserService userService = getUserAuthService();
        userService.findUserByUsername(new User().getUsername());
    }

    public static UserService getUserAuthService() {
        return new ProxyUserAuthService(new UserAuthService(userRepo));
    }
}

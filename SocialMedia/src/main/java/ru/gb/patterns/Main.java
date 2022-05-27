package ru.gb.patterns;

import ru.gb.patterns.builder.MultimediaBuilder;
import ru.gb.patterns.builder.MultimediaService;
import ru.gb.patterns.builder.User;
import ru.gb.patterns.observer.Bot;
import ru.gb.patterns.observer.Channel;
import ru.gb.patterns.proxy.ProxyUserAuthService;
import ru.gb.patterns.proxy.UserAuthService;
import ru.gb.patterns.proxy.UserRepo;
import ru.gb.patterns.proxy.UserService;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        User user = User.builder()
                .username("Username")
                .email("username@mail.ru")
                .build();

        MultimediaService multimedia = new MultimediaBuilder()
                .id(1L)
                .username("Multimedia")
                .contentType("Media file")
                .storageFileName("storage")
                .user(user)
                .build();

        System.out.println(multimedia.toString());

        Channel goodNewsChannel = new Channel("Good news channel");
        Channel badNewsChannel = new Channel("Bad news channel");

        ru.gb.patterns.proxy.User ivan = new ru.gb.patterns.proxy.User();
        Bot bot = new Bot();

        goodNewsChannel.subscribe(ivan);
        goodNewsChannel.subscribe(bot);
        badNewsChannel.subscribe(bot);

        goodNewsChannel.addNews("Today we have many good news");
        badNewsChannel.addNews("Everything is bad");

        UserService userService = getUserAuthService();
        userService.findUserByUsername(new ru.gb.patterns.proxy.User().getUsername());
    }

    public static UserService getUserAuthService() {
        UserRepo userRepo = username -> Optional.empty();

        return new ProxyUserAuthService(new UserAuthService(userRepo));
    }
}

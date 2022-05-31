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
import ru.gb.patterns.relationObject.UnitOfWork;

import java.util.Optional;

public class Main {

    private static UserRepo userRepo;

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
        ru.gb.patterns.proxy.User user2 = ru.gb.patterns.proxy.User.builder()
                .username("Username")
                .build();
        userRepo = username -> Optional.of(new ru.gb.patterns.proxy.User(user2.getUsername()));
        userService.findUserByUsername(userRepo.toString());

        UnitOfWork.newCurrent();
        ru.gb.patterns.proxy.User.load(1L).ifPresent(user1 -> user1.setJobId(2));
        ru.gb.patterns.proxy.User user1 = ru.gb.patterns.proxy.User.create("Anatoly", "password", 2L);
        UnitOfWork.getCurrent().commit();
        UnitOfWork.setCurrent(null);

        UnitOfWork.newCurrent();
        Optional<ru.gb.patterns.proxy.User> load = ru.gb.patterns.proxy.User.loadByName(user1.getUsername());
        if (load.isPresent()) {
            System.out.println("user1 created");
            load.get().remove();
        }
        UnitOfWork.getCurrent().commit();
        UnitOfWork.setCurrent(null);

        UnitOfWork.newCurrent();
        if (load.isPresent()) {
            load = ru.gb.patterns.proxy.User.load(load.get().getId());
        }
        if (load.isEmpty()) {
            System.out.println("user1 deleted");
        }
        UnitOfWork.getCurrent().commit();
        UnitOfWork.setCurrent(null);

        UnitOfWork.newCurrent();
        ru.gb.patterns.proxy.User.load(1L);
        System.out.println("Should be cached");
        ru.gb.patterns.proxy.User.load(1L);
        UnitOfWork.getCurrent().commit();
        UnitOfWork.setCurrent(null);
    }

    public static UserService getUserAuthService() {
        return new ProxyUserAuthService(new UserAuthService(userRepo));
    }
}

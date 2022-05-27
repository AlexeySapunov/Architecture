package ru.gb.patterns.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.patterns.observer.Channel;
import ru.gb.patterns.observer.Observable;
import ru.gb.patterns.observer.Subscriber;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class User implements Subscriber {

    private Long Id;

    private String username;

    private String password;

    @Override
    public void inform(Observable observable, Object message) {
        if (observable instanceof Channel) {
            Channel channel = (Channel) observable;
            System.out.println("I got news " + message + " from " + channel.getName());
            List<String> news = channel.getNews();
        }
    }
}

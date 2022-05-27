package ru.gb.patterns.observer;

public class Bot implements Subscriber {

    @Override
    public void inform(Observable observable, Object message) {
        if (observable instanceof Channel) {
            Channel channel = (Channel) observable;
            System.out.println("I'm processing message: " + message + " from " + channel.getName());
        }
    }
}

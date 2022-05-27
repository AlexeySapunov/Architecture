package ru.gb.patterns.observer;

public interface Observable {

    void subscribe(Subscriber subscriber);
}

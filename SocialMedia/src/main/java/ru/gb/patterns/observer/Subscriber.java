package ru.gb.patterns.observer;

public interface Subscriber {

    void inform(Observable observable, Object message);
}

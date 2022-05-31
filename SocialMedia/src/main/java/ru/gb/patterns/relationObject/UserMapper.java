package ru.gb.patterns.relationObject;

import ru.gb.patterns.proxy.User;

import java.util.Optional;

public interface UserMapper extends Mapper<User, Long> {

    Optional<User> findByName(String name);
}

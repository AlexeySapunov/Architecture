package ru.gb.patterns.proxy;

import java.util.List;

public interface UserService {

     List<UserType> findAllUsers();

     UserType findByUsername(String username);
}

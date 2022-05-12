package ru.gb.patterns.proxy;

import java.util.List;

public interface UserService {

     void create(UserFormType userFormType);

     List<UserType> findAllUsers();

     UserType findByUsername(String username);

     void setUserOnline(String username);

     void setUserOffline(String username);
}

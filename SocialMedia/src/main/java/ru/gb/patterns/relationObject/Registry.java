package ru.gb.patterns.relationObject;

import ru.gb.patterns.proxy.User;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private static Registry instance = new Registry();

    public static Registry getInstance() {
        return instance;
    }


    public static void reInit() {
        instance = new Registry();
    }


    Map<Class<? extends DomainObject<?>>, Mapper<? extends DomainObject<?>, ?>> map = new HashMap<>();

    {
        map.put(User.class, new UserMapperImpl());
    }

    public static <T extends DomainObject<ID>, ID> Mapper<T, ID> getMapper(Class<T> aClass) {
        return (Mapper<T, ID>) getInstance().map.get(aClass);
    }
}

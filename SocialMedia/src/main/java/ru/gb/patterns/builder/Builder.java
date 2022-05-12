package ru.gb.patterns.builder;

public interface Builder {

    Builder id(Long id);
    Builder username(String username);
    Builder contentType(String contentType);
    Builder storageFileName(String storageFileName);
    Builder user(User user);

    MultimediaService build();
}

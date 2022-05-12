package ru.gb.patterns.builder;

public class MultimediaServiceImpl implements MultimediaService {

    private final Long id;

    private final String username;

    private final String contentType;

    private final String storageFileName;

    private final User user;

    public MultimediaServiceImpl(Long id, String username, String contentType, String storageFileName, User user) {
        this.id = id;
        this.username = username;
        this.contentType = contentType;
        this.storageFileName = storageFileName;
        this.user = user;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String getStorageFileName() {
        return storageFileName;
    }

    @Override
    public User getUser() {
        return user;
    }
}

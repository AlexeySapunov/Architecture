package ru.gb.patterns.builder;

public class MultimediaBuilder implements Builder {

    private Long id;

    private String username;

    private String contentType;

    private String storageFileName;

    private User user;

    @Override
    public Builder id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Builder username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Builder contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public Builder storageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
        return this;
    }

    @Override
    public Builder user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public MultimediaService build() {
        return new MultimediaServiceImpl(id, username, contentType, storageFileName, user);
    }
}

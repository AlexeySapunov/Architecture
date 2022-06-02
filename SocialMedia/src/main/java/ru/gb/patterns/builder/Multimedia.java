package ru.gb.patterns.builder;

import javax.persistence.ManyToOne;

public class Multimedia {

    private Long id;

    private String name;

    private String contentType;

    private String storageFileName;

    @ManyToOne
    private User user;

    public Multimedia(Long id, String name, String contentType, String storageFileName, User user) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.storageFileName = storageFileName;
        this.user = user;
    }

    public Multimedia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

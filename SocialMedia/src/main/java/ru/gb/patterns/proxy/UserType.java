package ru.gb.patterns.proxy;

public class UserType {

    private Long id;

    private String username;

    private Boolean isOnline;

    public UserType(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public UserType withOnline(Boolean online) {
        isOnline = online;
        return this;
    }
}

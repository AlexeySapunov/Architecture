package ru.gb.patterns.builder;

public class MultimediaMain {

    public static void main(String[] args) {

        User user = User.builder()
                .username("Username")
                .email("username@mail.ru")
                .build();

        MultimediaService multimedia = new MultimediaBuilder()
                .id(1L)
                .username("Multimedia")
                .contentType("Media file")
                .storageFileName("storage")
                .user(user)
                .build();

        System.out.println(multimedia.toString());
    }
}

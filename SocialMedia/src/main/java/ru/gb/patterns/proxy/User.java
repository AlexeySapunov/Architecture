package ru.gb.patterns.proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long Id;

    private String username;

    private String password;
}

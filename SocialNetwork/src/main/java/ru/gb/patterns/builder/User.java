package ru.gb.patterns.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class User {

    private final Long id;

    private final String username;

    private final String email;

    private final String password;

    @OneToMany
    private final List<Multimedia> multimedia;
}

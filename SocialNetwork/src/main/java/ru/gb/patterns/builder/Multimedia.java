package ru.gb.patterns.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Multimedia {

    private final Long id;

    private final String username;

    private final String contentType;

    private final String storageFileName;

    @ManyToOne
    private final User user;
}

package ru.gb.patterns.builder;

import java.util.Optional;

public interface MultimediaService {

    Optional<MultimediaDto> getMultimediaById(long id);

    String create(byte[] multimedia);

    void deleteById(Long id);
}

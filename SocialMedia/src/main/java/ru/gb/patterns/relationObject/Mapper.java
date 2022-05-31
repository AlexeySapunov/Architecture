package ru.gb.patterns.relationObject;

import java.util.Optional;

public interface Mapper<T extends DomainObject<ID>, ID> {

    Optional<T> find(ID id);
    ID insert(T object);
    boolean delete(ID id);
    boolean update(T object);
}

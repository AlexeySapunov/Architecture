package ru.gb.patterns.relationObject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Job extends DomainObject<Long> {

    private final Long id;
    private final String title;
}

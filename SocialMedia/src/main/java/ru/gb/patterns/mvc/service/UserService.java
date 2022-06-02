package ru.gb.patterns.mvc.service;

import org.springframework.data.domain.Page;
import ru.gb.patterns.mvc.controller.dto.UserDto;
import ru.gb.patterns.mvc.controller.dto.UserListParams;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto userDto);

    void deleteById(Long id);
}

package ru.gb.patterns.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.patterns.mvc.controller.dto.UserDto;
import ru.gb.patterns.mvc.controller.dto.UserListParams;
import ru.gb.patterns.mvc.model.User;
import ru.gb.patterns.mvc.repo.UserRepository;
import ru.gb.patterns.mvc.repo.UserSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findWithFilter(UserListParams userListParams) {
        Specification<User> spec = Specification.where(null);

        if (userListParams.getUsernameFilter() != null && !userListParams.getUsernameFilter().isBlank()) {
            spec = spec.and(UserSpecification.nameLike(userListParams.getUsernameFilter()));
        }
        if (userListParams.getMinAge() != null) {
            spec = spec.and(UserSpecification.minAge(userListParams.getMinAge()));
        }
        if (userListParams.getMaxAge() != null) {
            spec = spec.and(UserSpecification.maxAge(userListParams.getMaxAge()));
        }

        return userRepository.findAll(spec,
                        PageRequest.of(
                                Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                                Optional.ofNullable(userListParams.getSize()).orElse(3),
                                Sort.by(Optional.ofNullable(userListParams.getSortField())
                                        .filter(c -> !c.isBlank())
                                        .orElse("id"))))
                .map(user -> new UserDto(user.getId(), user.getUsername()));
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()));
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

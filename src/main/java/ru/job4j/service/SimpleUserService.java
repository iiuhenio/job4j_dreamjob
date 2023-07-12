package ru.job4j.service;

import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import java.util.Optional;

public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    private final FileService fileService;

    public SimpleUserService(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}

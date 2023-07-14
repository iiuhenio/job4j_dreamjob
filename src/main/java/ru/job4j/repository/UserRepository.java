package ru.job4j.repository;

import ru.job4j.model.Candidate;
import ru.job4j.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean deleteById(int id);

    Optional<User> findById(int id);

    Collection<User> findAll();

}
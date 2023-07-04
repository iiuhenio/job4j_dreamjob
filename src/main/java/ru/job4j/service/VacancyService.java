package ru.job4j.service;

import ru.job4j.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface VacancyService {

    Vacancy save(Vacancy vacancy);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();

}
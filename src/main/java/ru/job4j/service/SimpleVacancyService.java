package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Vacancy;
import ru.job4j.repository.MemoryVacancyRepository;
import ru.job4j.repository.VacancyRepository;
import ru.job4j.service.VacancyService;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleVacancyService implements VacancyService {
    private final VacancyRepository vacancyRepository;

    public SimpleVacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return vacancyRepository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancyRepository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return vacancyRepository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

}
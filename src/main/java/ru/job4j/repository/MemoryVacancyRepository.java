package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Vacancy;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class MemoryVacancyRepository implements VacancyRepository {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    public MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Стажер разработчик", LocalDateTime.now(), false));
        save(new Vacancy(0, "Junior Java Developer", "Младший разработчик", LocalDateTime.now(), true));
        save(new Vacancy(0, "Junior+ Java Developer", "Java разработчик", LocalDateTime.now(), false));
        save(new Vacancy(0, "Middle Java Developer", "Старший разработчик", LocalDateTime.now(), true));
        save(new Vacancy(0, "Middle+ Java Developer", "Ведущий разработчик", LocalDateTime.now(), true));
        save(new Vacancy(0, "Senior Java Developer", "Главный разработчик", LocalDateTime.now(), true));
    }


    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId.getAndIncrement());
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> new Vacancy(oldVacancy.getId(),
                vacancy.getTitle(), vacancy.getDescription(), vacancy.getCreationDate(), vacancy.getVisible())) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }

}
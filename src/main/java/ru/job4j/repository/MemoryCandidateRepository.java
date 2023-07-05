package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Candidate;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Intern Java Developer", "Стажер", LocalDateTime.now()));
        save(new Candidate(0, "Junior Java Developer", "Младший сотрудник", LocalDateTime.now()));
        save(new Candidate(0, "Junior+ Java Developer",  "Старший сотрудник", LocalDateTime.now()));
        save(new Candidate(0, "Middle Java Developer",  "Заместитель", LocalDateTime.now()));
        save(new Candidate(0, "Middle+ Java Developer",  "Ведущий програмист", LocalDateTime.now()));
        save(new Candidate(0, "Senior Java Developer",  "Главный сотрудник", LocalDateTime.now()));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldVacancy) -> new Candidate(oldVacancy.getId(),
                candidate.getName(), candidate.getDescription(), candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }

}
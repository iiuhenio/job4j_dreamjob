package ru.job4j.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.model.Candidate;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Repository
@ThreadSafe
public class MemoryCandidateRepository implements CandidateRepository {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Intern Java Developer", "Стажер",
                LocalDateTime.now(), 1));
        save(new Candidate(0, "Junior Java Developer", "Младший сотрудник",
                LocalDateTime.now(), 2));
        save(new Candidate(0, "Junior+ Java Developer",  "Старший сотрудник",
                LocalDateTime.now(), 3));
        save(new Candidate(0, "Middle Java Developer",  "Заместитель",
                LocalDateTime.now(), 1));
        save(new Candidate(0, "Middle+ Java Developer",  "Ведущий програмист",
                LocalDateTime.now(), 2));
        save(new Candidate(0, "Senior Java Developer",  "Главный сотрудник",
                LocalDateTime.now(), 3));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.getAndIncrement());
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldVacancy)
                -> new Candidate(oldVacancy.getId(),
                candidate.getName(),
                candidate.getDescription(),
                candidate.getCreationDate(),
                candidate.getCityId()))
                != null;
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
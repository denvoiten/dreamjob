package ru.job4j.store;

import ru.job4j.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
        posts.put(1, new Candidate(1, "Junior Java Job", "Junior Java Candidate", LocalDate.now()));
        posts.put(2, new Candidate(2, "Middle Java Job", "Middle Java Candidate", LocalDate.now()));
        posts.put(3, new Candidate(3, "Senior Java Job", "Senior Java Candidate", LocalDate.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return posts.values();
    }
}

package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.Main;
import ru.job4j.model.Candidate;
import ru.job4j.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {
    private final CandidateDBStore store = new CandidateDBStore(new Main().loadPool());

    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Ivan");
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenCreateTwoAndFindAll() {
        Candidate candidate = new Candidate(0, "Ivan");
        Candidate candidate2 = new Candidate(1, "Sergey");
        store.add(candidate);
        store.add(candidate2);
        List<Candidate> expected = List.of(candidate, candidate2);
        assertThat(expected, is(store.findAll()));
    }

    @Test
    public void whenUpdatePost() {
        Candidate candidate = new Candidate(0, "Ivan");
        store.add(candidate);
        Candidate candidate2 = new Candidate(candidate.getId(), "Sergey");
        store.update(candidate2);
        assertThat(store.findById(candidate.getId()), is(candidate2));
        assertThat(store.findById(candidate.getId()).getName(), is(candidate2.getName()));
    }
}
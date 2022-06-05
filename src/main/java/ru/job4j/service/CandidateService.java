package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Candidate;
import ru.job4j.persistence.CandidateDBStore;

import java.util.List;

@ThreadSafe
@Service
public class CandidateService {

    private final CandidateDBStore candidateDBStore;
    private final CityService cityService;

    public CandidateService(CandidateDBStore candidateDBStore, CityService cityService) {
        this.candidateDBStore = candidateDBStore;
        this.cityService = cityService;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = candidateDBStore.findAll();
        candidates.forEach(
                candidate -> candidate.setCity(
                        cityService.findById(candidate.getCity().getId())
                )
        );
        return candidates;
    }

    public void add(Candidate candidate) {
        candidateDBStore.add(candidate);
    }

    public Candidate findById(int id) {
        return candidateDBStore.findById(id);
    }

    public void update(Candidate candidate) {
        candidateDBStore.update(candidate);
    }

    public void delete(Candidate candidate) {
        candidateDBStore.delete(candidate);
    }
}

package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Candidate;
import ru.job4j.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class CandidateDBStore {

    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidates")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getBytes("photo"),
                            it.getString("description"),
                            new City(it.getInt("city_id")),
                            it.getString("created")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO candidates(name, photo, description, city_id, created) VALUES (?, ?, ?, ?, ?)",
                     RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setBytes(2, candidate.getPhoto());
            ps.setString(3, candidate.getDescription());
            ps.setInt(4, candidate.getCity().getId());
            ps.setString(5, LocalDateTime.now().toString().split("T")[0]);
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE candidates SET name = ?, photo = ?, description = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setBytes(2, candidate.getPhoto());
            ps.setString(3, candidate.getDescription());
            ps.setInt(4, candidate.getCity().getId());
            ps.setInt(5, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        Candidate candidate = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidates WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    candidate = new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getBytes("photo"),
                            it.getString("description"),
                            new City(it.getInt("city_id")),
                            it.getString("created"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }
}

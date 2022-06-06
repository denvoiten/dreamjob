package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class UserDBStore {
    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());

    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)",
                     RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
            rsl = Optional.of(user);
        } catch (Exception e) {
            LOG.error("Exception in ADD USER method", e);
        }
        return rsl;
    }
}

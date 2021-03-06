package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.City;
import ru.job4j.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class PostDBStore {
    private static final Logger LOG = LoggerFactory.getLogger(PostDBStore.class.getName());

    private final BasicDataSource pool;

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post ORDER BY id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id")),
                            it.getString("created")));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in FIND ALL POST method", e);
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO post(name, description, visible, city_id, created) VALUES (?, ?, ?, ?, ?)",
                     RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setBoolean(3, post.isVisible());
            ps.setInt(4, post.getCity().getId());
            ps.setString(5, LocalDateTime.now().toString().split("T")[0]);
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in ADD POST method", e);
        }
        return post;
    }

    public void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE post SET name = ?, description = ?, visible = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setBoolean(3, post.isVisible());
            ps.setInt(4, post.getCity().getId());
            ps.setInt(5, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Exception in UPDATE POST method", e);
        }
    }

    public Post findById(int id) {
        Post post = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    post = new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id")),
                            it.getString("created"));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in FIND BY ID POST method", e);
        }
        return post;
    }

    public void delete(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM post WHERE id = ?")
        ) {
            ps.setInt(1, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("Exception in DELETE POST method", e);
        }
    }
}

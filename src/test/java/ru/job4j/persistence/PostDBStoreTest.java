package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.Main;
import ru.job4j.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {
    private final PostDBStore store = new PostDBStore(new Main().loadPool());

    @Test
    public void whenCreatePost() {
        Post post = new Post(0, "Java Job");
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateTwoAndFindAll() {
        Post post = new Post(0, "Java Job");
        Post post2 = new Post(1, "Java Job for Middle");
        store.add(post);
        store.add(post2);
        List<Post> expected = List.of(post, post2);
        assertThat(expected, is(store.findAll()));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(0, "Java Job");
        store.add(post);
        Post post2 = new Post(post.getId(), "Java Job for Middle");
        store.update(post2);
        assertThat(store.findById(post.getId()), is(post2));
        assertThat(store.findById(post.getId()).getName(), is(post2.getName()));
    }
}
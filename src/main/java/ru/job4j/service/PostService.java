package ru.job4j.service;

import ru.job4j.model.Post;
import ru.job4j.persistence.PostStore;

import java.util.Collection;

public class PostService {

    private static final PostService INST = new PostService();

    private final PostStore postStore = PostStore.instOf();

    public static PostService instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return postStore.findAll();
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public void update(Post post) {
        postStore.update(post);
    }
}

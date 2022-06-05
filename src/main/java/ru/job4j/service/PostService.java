package ru.job4j.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.model.Post;
import ru.job4j.persistence.PostDBStore;

import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostDBStore postDBStore;
    private final CityService cityService;

    public PostService(PostDBStore postDBStore, CityService cityService) {
        this.postDBStore = postDBStore;
        this.cityService = cityService;
    }

    public List<Post> findAll() {
        List<Post> posts = postDBStore.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }

    public void add(Post post) {
        postDBStore.add(post);
    }

    public Post findById(int id) {
        return postDBStore.findById(id);
    }

    public void update(Post post) {
        postDBStore.update(post);
    }

    public void delete(Post post) {
        postDBStore.delete(post);
    }
}

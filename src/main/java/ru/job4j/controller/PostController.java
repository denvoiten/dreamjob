package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Post;
import ru.job4j.store.PostStore;

@Controller
public class PostController {

    private final PostStore store = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }
}

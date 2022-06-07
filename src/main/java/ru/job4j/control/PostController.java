package ru.job4j.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.service.CityService;
import ru.job4j.service.PostService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class PostController {
    private static final String REDIRECT = "redirect:/posts";
    private static final String GUEST = "Гость";

    private final PostService postService;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    private void setUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName(GUEST);
        }
        model.addAttribute("user", user);
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        model.addAttribute("posts", postService.findAll());
        setUser(model, session);
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        model.addAttribute("post", new Post());
        model.addAttribute("cities", cityService.getAllCities());
        setUser(model, session);
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.add(post);
        return REDIRECT;
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id, HttpSession session) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        setUser(model, session);
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.update(post);
        return REDIRECT;
    }

    @PostMapping("/posts/{postId}/delete")
    public String deletePost(@PathVariable(value = "postId") int id) {
        Post post = postService.findById(id);
        postService.delete(post);
        return REDIRECT;
    }
}

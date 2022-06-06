package ru.job4j.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.User;
import ru.job4j.service.CandidateService;
import ru.job4j.service.PostService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class IndexControl {

    private final PostService postService;

    private final CandidateService candidateService;

    public IndexControl(PostService postService, CandidateService candidateService) {
        this.postService = postService;
        this.candidateService = candidateService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("candidates", candidateService.findAll());
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        return "index";
    }
}
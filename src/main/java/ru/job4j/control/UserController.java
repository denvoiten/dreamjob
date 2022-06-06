package ru.job4j.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import java.util.Optional;

@ThreadSafe
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }

    @GetMapping("/fail")
    public String failForm(Model model) {
        model.addAttribute("message", "Ошибка регистрации!");
        return "fail";
    }

    @GetMapping("/success")
    public String successForm(Model model) {
        model.addAttribute("message", "Регистрация прошла успешно!");
        return "success";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }
}

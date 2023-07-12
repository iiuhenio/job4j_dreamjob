package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dto.FileDto;
import ru.job4j.model.User;
import ru.job4j.model.Vacancy;
import ru.job4j.service.CityService;
import ru.job4j.service.UserService;
import ru.job4j.service.VacancyService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getCreationPage(Model model) {
        model.addAttribute("users");
        return "users/register";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }
}

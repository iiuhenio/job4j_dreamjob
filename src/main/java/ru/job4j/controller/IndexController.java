package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.User;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }
}
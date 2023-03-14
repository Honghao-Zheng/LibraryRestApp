package com.sparta.mg.librarywebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class WelcomeController {

    @GetMapping("/") //localhost:5000/
    public String welcome() {
        return "../templates/home";
    }

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(defaultValue = "World", required = false) String name) {
        model.addAttribute("dateTime", LocalDateTime.now());
        model.addAttribute("name", name);
        return "hello";
    }
}

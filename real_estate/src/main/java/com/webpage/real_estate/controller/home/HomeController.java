package com.webpage.real_estate.controller.home;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String homepage(HttpSession session) {
        String email = (String) session.getAttribute("email");
        return "home";
    }
}

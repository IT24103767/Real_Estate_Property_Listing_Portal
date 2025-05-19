package com.webpage.real_estate.controller.login;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {
        if (file_management.validateUser(email, password)) {
            User user = file_management.getUserByEmail(email);
            if (user != null) {
                session.setAttribute("email", email);
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                return "redirect:/dashboard";
            }
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}

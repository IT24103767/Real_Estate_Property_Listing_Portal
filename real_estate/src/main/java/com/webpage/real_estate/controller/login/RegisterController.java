package com.webpage.real_estate.controller.login;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(HttpSession session, @RequestParam String username, @RequestParam String email, @RequestParam String password,@RequestParam String phone) {
        User user = new User(username, email, password, phone);
        if (!file_management.Email(user)){
                file_management.writeUser(user);
                session.setAttribute("email", user.getEmail());
                return "redirect:/home";
        }
        return "register";
    }
}

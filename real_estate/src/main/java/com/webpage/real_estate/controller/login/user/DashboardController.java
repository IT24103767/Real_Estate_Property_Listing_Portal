package com.webpage.real_estate.controller.login.user;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        // Get user data from file management
        User user = file_management.getUserByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "dashboard";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(HttpSession session, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        
        if (email == null) {
            return "redirect:/login";
        }

        try {
            boolean deleted = file_management.deleteUser(email);
            if (deleted) {
                session.invalidate(); // Log out the user
                return "redirect:/login?deleted=true";
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to delete account. Please try again.");
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/dashboard";
        }
    }
}

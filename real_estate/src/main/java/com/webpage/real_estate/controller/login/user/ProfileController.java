package com.webpage.real_estate.controller.login.user;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        // Get user data from file management
        User user = file_management.getUserByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
        }

        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String username,
                              @RequestParam String phone,
                              @RequestParam(required = false) String currentPassword,
                              @RequestParam(required = false) String newPassword,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        // Get current user data
        User currentUser = file_management.getUserByEmail(email);
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/profile";
        }

        // Validate current password if provided
        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (!file_management.validateUser(email, currentPassword)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect");
                return "redirect:/profile";
            }
        }

        // Update user information
        currentUser.setUsername(username);
        currentUser.setPhone(phone);
        if (newPassword != null && !newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
        }

        // Save updated user information
        if (file_management.updateUser(currentUser)) {
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile");
        }

        return "redirect:/profile";
    }
}

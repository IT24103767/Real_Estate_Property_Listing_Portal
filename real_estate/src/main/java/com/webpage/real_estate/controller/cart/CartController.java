package com.webpage.real_estate.controller.cart;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        System.out.println("Viewing cart for user: " + email);
        
        if (email == null) {
            return "redirect:/login";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Current cart: " + (cart != null ? "Found with " + cart.getItemCount() + " items" : "Not found"));
        
        if (cart == null) {
            cart = new Cart(email);
            session.setAttribute("cart", cart);
            System.out.println("Created new cart for user: " + email);
        }

        List<String> cartProperties = new ArrayList<>();
        for (String propertyId : cart.getPropertyIds()) {
            String property = file_management.getProperties().stream()
                    .filter(p -> p.split(",")[0].equals(propertyId))
                    .findFirst()
                    .orElse(null);
            if (property != null) {
                cartProperties.add(property);
                System.out.println("Added property to view: " + propertyId);
            }
        }

        model.addAttribute("properties", cartProperties);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        System.out.println("Adding to cart - Property ID: " + id);
        
        if (email == null) {
            System.out.println("No email in session - redirecting to login");
            return "redirect:/login";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Current cart: " + (cart != null ? "Found with " + cart.getItemCount() + " items" : "Not found"));
        
        if (cart == null) {
            cart = new Cart(email);
            System.out.println("Created new cart for user: " + email);
        }

        // Check if property exists and is not owned by the current user
        String property = file_management.getProperties().stream()
                .filter(p -> {
                    String[] parts = p.split(",");
                    return parts.length > 2 && parts[0].equals(id) && !parts[2].equals(email);
                })
                .findFirst()
                .orElse(null);

        if (property != null && !cart.containsProperty(id)) {
            cart.addProperty(id);
            session.setAttribute("cart", cart);  // Save cart back to session
            System.out.println("Added property " + id + " to cart. New count: " + cart.getItemCount());
            redirectAttributes.addFlashAttribute("success", "Property added to cart!");
        } else if (cart.containsProperty(id)) {
            System.out.println("Property " + id + " already in cart");
            redirectAttributes.addFlashAttribute("error", "Property is already in your cart.");
        } else {
            System.out.println("Property " + id + " not found or owned by user");
            redirectAttributes.addFlashAttribute("error", "Property not found or you own this property.");
        }

        return "redirect:/buy";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Removing from cart - Property ID: " + id);
        System.out.println("Current cart: " + (cart != null ? "Found with " + cart.getItemCount() + " items" : "Not found"));
        
        if (cart != null) {
            cart.removeProperty(id);
            session.setAttribute("cart", cart);  // Save cart back to session
            System.out.println("Removed property " + id + " from cart. New count: " + cart.getItemCount());
            redirectAttributes.addFlashAttribute("success", "Property removed from cart!");
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session, RedirectAttributes redirectAttributes) {
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Clearing cart");
        System.out.println("Current cart: " + (cart != null ? "Found with " + cart.getItemCount() + " items" : "Not found"));
        
        if (cart != null) {
            cart.clearCart();
            session.setAttribute("cart", cart);  // Save cart back to session
            System.out.println("Cart cleared. New count: " + cart.getItemCount());
            redirectAttributes.addFlashAttribute("success", "Cart cleared!");
        }
        return "redirect:/cart";
    }
} 
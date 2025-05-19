package com.webpage.real_estate.controller.buy;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @GetMapping("")
    public String showBuyPage(
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String priceSort,
            Model model,
            HttpSession session) {
        
        String email = (String) session.getAttribute("email");
        List<String> propertyTypes = List.of("house", "rent", "apartment", "land");
        List<String> properties = file_management.getProperties();
        
        if (properties == null) {
            properties = new ArrayList<>();
        }

        try {
            if (email != null) {
                properties = properties.stream()
                        .filter(p -> {
                            String[] parts = p.split(",");
                            return parts.length > 2 && !parts[2].equals(email);
                        })
                        .collect(Collectors.toList());
            }

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null) {
                properties = properties.stream()
                        .filter(p -> {
                            String[] parts = p.split(",");
                            return parts.length > 0 && !cart.containsProperty(parts[0]);
                        })
                        .collect(Collectors.toList());
            }

            if (propertyType != null && !propertyType.equals("all")) {
                properties = properties.stream()
                        .filter(p -> {
                            String[] parts = p.split(",");
                            return parts.length > 1 && parts[1].trim().equalsIgnoreCase(propertyType.trim());
                        })
                        .collect(Collectors.toList());
            }

            if (priceSort != null && !priceSort.equals("none")) {
                properties.sort((p1, p2) -> {
                    try {
                        String[] parts1 = p1.split(",");
                        String[] parts2 = p2.split(",");
                        
                        double price1 = parts1[1].equals("rent") ? Double.parseDouble(parts1[9].trim()) : Double.parseDouble(parts1[5].trim());
                        double price2 = parts2[1].equals("rent") ? Double.parseDouble(parts2[9].trim()) : Double.parseDouble(parts2[5].trim());
                        
                        return priceSort.equals("asc") ? Double.compare(price1, price2) : Double.compare(price2, price1);
                    } catch (Exception e) {
                        return 0;
                    }
                });
            }

        } catch (Exception e) {
            properties = new ArrayList<>();
        }

        model.addAttribute("properties", properties);
        model.addAttribute("propertyTypes", propertyTypes);
        model.addAttribute("selectedPropertyType", propertyType);
        model.addAttribute("selectedPriceSort", priceSort);

        return "buy";
    }
} 
package com.webpage.real_estate.controller.sell;

import com.webpage.real_estate.file_management.file_management;
import com.webpage.real_estate.model.House;
import com.webpage.real_estate.model.Apartment;
import com.webpage.real_estate.model.Land;
import com.webpage.real_estate.model.Rent;
import com.webpage.real_estate.model.Property;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/sell")
public class SellController {

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";
    private static final String IMAGE_RESOURCE_PATH = "/images/";

    private void ensureUploadDirectoryExists() {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("")
    public String showSellPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        
        // Get properties for the logged-in user
        List<String> properties = file_management.getPropertiesForEmail(email);
        System.out.println("\n=== Sell Page Debug ===");
        System.out.println("User email: " + email);
        System.out.println("User properties found: " + properties.size());
        for (String prop : properties) {
            System.out.println("Property: " + prop);
        }
        
        // Add property types for the form
        List<String> propertyTypes = List.of("house", "rent", "apartment", "land");
        
        model.addAttribute("properties", properties);
        model.addAttribute("propertyTypes", propertyTypes);
        model.addAttribute("isEditing", false);
        
        return "sell";
    }

    @PostMapping("/add")
    public String addProperty(@RequestParam String type,
                              @RequestParam String title,
                              @RequestParam String location,
                              @RequestParam String description,
                              @RequestParam(required = false) String price,
                              @RequestParam(required = false) String monthlyPrice,
                              @RequestParam(required = false) String nightlyPrice,
                              @RequestParam(required = false) String squareFootage,
                              @RequestParam(required = false) String bedrooms,
                              @RequestParam(required = false) String bathrooms,
                              @RequestParam(required = false) String rooms,
                              @RequestParam(required = false) String floorNumber,
                              @RequestParam(required = false) String hasElevator,
                              @RequestParam(required = false) String hasParking,
                              @RequestParam(required = false) String minDays,
                              @RequestParam(required = false) String garage,
                              @RequestParam(required = false) String yearBuilt,
                              @RequestParam(required = false) String isFurnished,
                              @RequestParam(required = false) String hasUtilities,
                              @RequestParam(required = false) String isFenced,
                              @RequestParam("propertyImage") MultipartFile propertyImage,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Title is required.");
            return "redirect:/sell";
        }

        if (location == null || location.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Location is required.");
            return "redirect:/sell";
        }

        if (description == null || description.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Description is required.");
            return "redirect:/sell";
        }

        ensureUploadDirectoryExists();

        String imagePath = "";
        if (!propertyImage.isEmpty()) {
            try {
                String filename = System.currentTimeMillis() + "_" + propertyImage.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
                Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
                Path filePath = uploadPath.resolve(filename);
                Files.copy(propertyImage.getInputStream(), filePath);
                imagePath = IMAGE_RESOURCE_PATH + filename;
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload image.");
                return "redirect:/sell";
            }
        }

        double priceVal = parseDoubleOrDefault(price, 0);
        double landSizeVal = parseDoubleOrDefault(squareFootage, 0);
        int bedroomsVal = parseIntOrDefault(bedrooms, 0);
        int bathroomsVal = parseIntOrDefault(bathrooms, 0);
        int roomsVal = parseIntOrDefault(rooms, 0);
        int floorNumberVal = parseIntOrDefault(floorNumber, 0);
        boolean hasElevatorVal = "true".equalsIgnoreCase(hasElevator);
        boolean hasParkingVal = "true".equalsIgnoreCase(hasParking);
        int minDaysVal = parseIntOrDefault(minDays, 0);
        double monthlyPriceVal = parseDoubleOrDefault(monthlyPrice, 0);
        double nightlyPriceVal = parseDoubleOrDefault(nightlyPrice, 0);
        int garageVal = parseIntOrDefault(garage, 0);
        int yearBuiltVal = parseIntOrDefault(yearBuilt, 0);
        boolean isFurnishedVal = "true".equalsIgnoreCase(isFurnished);
        boolean hasUtilitiesVal = "true".equalsIgnoreCase(hasUtilities);
        boolean isFencedVal = "true".equalsIgnoreCase(isFenced);

        Property propertyData;

        switch (type.toLowerCase()) {
            case "house":
                propertyData = new House(title, description, landSizeVal, priceVal, location, imagePath, 
                    bedroomsVal, bathroomsVal);
                break;

            case "apartment":
                propertyData = new Apartment(title, description, landSizeVal, priceVal, location, imagePath,
                    floorNumberVal, roomsVal, hasElevatorVal);
                break;

            case "rent":
                propertyData = new Rent(title, description, landSizeVal, monthlyPriceVal, location, imagePath,
                    monthlyPriceVal, nightlyPriceVal, minDaysVal);
                break;

            case "land":
                propertyData = new Land(title, description, landSizeVal, priceVal, location, imagePath,
                    hasUtilitiesVal, isFencedVal);
                break;

            default:
                redirectAttributes.addFlashAttribute("error", "Invalid property type.");
                return "redirect:/sell";
        }

        try {
            file_management.writeProperty(propertyData, session, type.toLowerCase());
            redirectAttributes.addFlashAttribute("success", "Property added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add property.");
        }

        return "redirect:/sell";
    }

    @PostMapping("/update/{id}/test")
    @ResponseBody
    public String testUpdate(@PathVariable String id) {
        return "Update endpoint hit for ID: " + id;
    }

    @PostMapping("/update/{id}")
    public String updateProperty(@PathVariable String id,
                                 @RequestParam String type,
                                 @RequestParam String title,
                                 @RequestParam String location,
                                 @RequestParam String description,
                                 @RequestParam(required = false) String price,
                                 @RequestParam(required = false) String monthlyPrice,
                                 @RequestParam(required = false) String nightlyPrice,
                                 @RequestParam(required = false) String squareFootage,
                                 @RequestParam(required = false) String bedrooms,
                                 @RequestParam(required = false) String bathrooms,
                                 @RequestParam(required = false) String rooms,
                                 @RequestParam(required = false) String floorNumber,
                                 @RequestParam(required = false) String hasElevator,
                                 @RequestParam(required = false) String hasParking,
                                 @RequestParam(required = false) String minDays,
                                 @RequestParam(required = false) String hasUtilities,
                                 @RequestParam(required = false) String isFenced,
                                 @RequestParam(value = "propertyImage", required = false) MultipartFile propertyImage,
                                 @RequestParam(value = "currentImage", required = false) String currentImage,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        System.out.println("\n=== Property Update Debug ===");
        System.out.println("Updating property ID: " + id);
        System.out.println("Type: " + type);
        System.out.println("Title: " + title);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("Current Image Path: " + currentImage);
        System.out.println("New Image Uploaded: " + (propertyImage != null && !propertyImage.isEmpty()));

        // Validate required fields
        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Title is required.");
            return "redirect:/sell";
        }

        if (location == null || location.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Location is required.");
            return "redirect:/sell";
        }

        if (description == null || description.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Description is required.");
            return "redirect:/sell";
        }

        // Handle image path
        String imagePath = currentImage; // Default to current image
        if (propertyImage != null && !propertyImage.isEmpty()) {
            try {
                ensureUploadDirectoryExists();
                String filename = System.currentTimeMillis() + "_" + propertyImage.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
                Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
                Path filePath = uploadPath.resolve(filename);
                Files.copy(propertyImage.getInputStream(), filePath);
                imagePath = IMAGE_RESOURCE_PATH + filename;
                System.out.println("New image path: " + imagePath);
            } catch (IOException e) {
                System.out.println("Error uploading new image: " + e.getMessage());
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload image: " + e.getMessage());
                return "redirect:/sell";
            }
        }

        // Parse numeric values
        double priceVal = parseDoubleOrDefault(price, 0);
        double landSizeVal = parseDoubleOrDefault(squareFootage, 0);
        int bedroomsVal = parseIntOrDefault(bedrooms, 0);
        int bathroomsVal = parseIntOrDefault(bathrooms, 0);
        int roomsVal = parseIntOrDefault(rooms, 0);
        int floorNumberVal = parseIntOrDefault(floorNumber, 0);
        boolean hasElevatorVal = "true".equalsIgnoreCase(hasElevator);
        int minDaysVal = parseIntOrDefault(minDays, 0);
        double monthlyPriceVal = parseDoubleOrDefault(monthlyPrice, 0);
        double nightlyPriceVal = parseDoubleOrDefault(nightlyPrice, 0);
        boolean hasUtilitiesVal = "true".equalsIgnoreCase(hasUtilities);
        boolean isFencedVal = "true".equalsIgnoreCase(isFenced);

        System.out.println("Parsed values:");
        System.out.println("Price: " + priceVal);
        System.out.println("Land Size: " + landSizeVal);
        System.out.println("Type-specific values based on " + type + ":");
        switch (type.toLowerCase()) {
            case "house":
                System.out.println("Bedrooms: " + bedroomsVal);
                System.out.println("Bathrooms: " + bathroomsVal);
                break;
            case "apartment":
                System.out.println("Floor Number: " + floorNumberVal);
                System.out.println("Rooms: " + roomsVal);
                System.out.println("Has Elevator: " + hasElevatorVal);
                break;
            case "rent":
                System.out.println("Monthly Price: " + monthlyPriceVal);
                System.out.println("Nightly Price: " + nightlyPriceVal);
                System.out.println("Min Days: " + minDaysVal);
                break;
            case "land":
                System.out.println("Has Utilities: " + hasUtilitiesVal);
                System.out.println("Is Fenced: " + isFencedVal);
                break;
        }

        Property propertyData;
        try {
            switch (type.toLowerCase()) {
                case "house":
                    propertyData = new House(title, description, landSizeVal, priceVal, location, imagePath, bedroomsVal, bathroomsVal);
                    break;

                case "apartment":
                    propertyData = new Apartment(title, description, landSizeVal, priceVal, location, imagePath, floorNumberVal, roomsVal, hasElevatorVal);
                    break;

                case "rent":
                    propertyData = new Rent(title, description, landSizeVal, monthlyPriceVal, location, imagePath, monthlyPriceVal, nightlyPriceVal, minDaysVal);
                    break;

                case "land":
                    propertyData = new Land(title, description, landSizeVal, priceVal, location, imagePath, hasUtilitiesVal, isFencedVal);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid property type: " + type);
            }

            file_management.updateProperty(id, propertyData, session, type.toLowerCase());
            System.out.println("Property updated successfully!");
            redirectAttributes.addFlashAttribute("success", "Property updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating property: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to update property: " + e.getMessage());
        }

        return "redirect:/sell";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        
        // Get properties for the logged-in user
        List<String> properties = file_management.getPropertiesForEmail(email);
        String propertyToEdit = null;
        
        // Find the property to edit
        for (String prop : properties) {
            String[] parts = prop.split(",");
            if (parts[0].equals(id)) {
                propertyToEdit = prop;
                break;
            }
        }
        
        if (propertyToEdit == null) {
            return "redirect:/sell";
        }
        
        // Add necessary attributes to the model
        List<String> propertyTypes = List.of("house", "rent", "apartment", "land");
        model.addAttribute("properties", properties != null ? properties : new ArrayList<>());
        model.addAttribute("property", propertyToEdit);
        model.addAttribute("propertyTypes", propertyTypes);
        model.addAttribute("isEditing", true);
        model.addAttribute("editId", id);
        
        System.out.println("Editing property: " + propertyToEdit);
        return "sell";
    }

    @PostMapping("/delete/{id}")
    public String deleteProperty(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        System.out.println("Attempting to delete property: " + id);
        
        try {
            if (file_management.deleteProperty(id, session)) {
                redirectAttributes.addFlashAttribute("success", "Property deleted successfully!");
                System.out.println("Property deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to delete property. Property not found or unauthorized.");
                System.out.println("Failed to delete property");
            }
        } catch (Exception e) {
            System.out.println("Error deleting property: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the property.");
        }

        return "redirect:/sell";
    }

    // Helper methods
    private double parseDoubleOrDefault(String str, double defaultVal) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private int parseIntOrDefault(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultVal;
        }
    }
}

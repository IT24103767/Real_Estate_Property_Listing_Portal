package com.example.oop_project_real_estate.properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Controller
public class PropertyController {

    @GetMapping("/add-property")
    public String showPropertyForm() {
        return "property";
    }

    @PostMapping("/home")
    public String submitProperty(@RequestParam("propertyImage") MultipartFile image,
                                 @RequestParam("title") String title,
                                 @RequestParam("location") String location,
                                 @RequestParam("price") double price,
                                 @RequestParam("description") String description,
                                 Model model) throws IOException {

        // Save image to a local folder
        String uploadDir = "src/main/resources/static/uploads/";
        String fileName = image.getOriginalFilename();
        File saveFile = new File(uploadDir + fileName);
        image.transferTo(saveFile);

        // Create Property object
        Property property = new Property();
        property.setLocation(location);
        property.setPrice(price);
        property.setDescription(description);
        property.setImagePath("/uploads/" + fileName);


        // Send to view
        model.addAttribute("property", property);
        return "property_success";
    }
}

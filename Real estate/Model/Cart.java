package com.webpage.real_estate.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Cart implements Serializable {
    private List<String> propertyIds;
    private String userEmail;

    public Cart(String userEmail) {
        this.propertyIds = new ArrayList<>();
        this.userEmail = userEmail;
        System.out.println("Created new cart for user: " + userEmail);
    }

    public void addProperty(String propertyId) {
        System.out.println("Adding property to cart: " + propertyId);
        if (!propertyIds.contains(propertyId)) {
            propertyIds.add(propertyId);
            System.out.println("Property added successfully. Cart now has " + propertyIds.size() + " items");
        } else {
            System.out.println("Property already in cart");
        }
    }

    public void removeProperty(String propertyId) {
        System.out.println("Removing property from cart: " + propertyId);
        boolean removed = propertyIds.remove(propertyId);
        System.out.println(removed ? "Property removed successfully" : "Property not found in cart");
        System.out.println("Cart now has " + propertyIds.size() + " items");
    }

    public List<String> getPropertyIds() {
        return new ArrayList<>(propertyIds); // Return a copy to prevent external modification
    }

    public void clearCart() {
        System.out.println("Clearing cart for user: " + userEmail);
        propertyIds.clear();
        System.out.println("Cart cleared");
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean containsProperty(String propertyId) {
        boolean contains = propertyIds.contains(propertyId);
        System.out.println("Checking if cart contains property " + propertyId + ": " + contains);
        return contains;
    }

    public int getItemCount() {
        return propertyIds.size();
    }
} 
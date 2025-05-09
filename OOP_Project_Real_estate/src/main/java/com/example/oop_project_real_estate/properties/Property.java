package com.example.oop_project_real_estate.properties;

import com.example.oop_project_real_estate.textfile.textfile;

public class Property {

    textfile file = new textfile("property");
    private String propertyId;
    private String location;
    private double landSize;
    private double price;
    private String description;
    private String ownerName;
    private String ownerContact;
    private boolean isAvailable;
    private String propertyType;
    private String imagePath;

    public Property() {
    }

    public Property(String propertyId, String location, double landSize,
                    double price, String description, String ownerName, String ownerContact) {
        this.propertyId = propertyId;
        this.location = location;
        this.landSize = landSize;
        this.price = price;
        this.description = description;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.isAvailable = true;
    }

    public Property(String propertyId, String location, double landSize, double price, String description, String ownerName, String ownerContact, String propertyType) {
        this.propertyId = propertyId;
        this.location = location;
        this.landSize = landSize;
        this.price = price;
        this.description = description;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.propertyType = propertyType;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLandSize() {
        return landSize;
    }

    public void setLandSize(double landSize) {
        this.landSize = landSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getPropertyType() {
        return propertyType;
    }

    protected void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void displayDetails() {
        System.out.println("Property ID: " + propertyId);
        System.out.println("Location: " + location);
        System.out.println("Land Size: " + landSize + " sq.m");
        System.out.println("Price: $" + price);
        System.out.println("Description: " + description);
        System.out.println("Owner: " + ownerName);
        System.out.println("Contact: " + ownerContact);
        System.out.println("Status: " + (isAvailable ? "Available" : "Not Available"));
    }
}
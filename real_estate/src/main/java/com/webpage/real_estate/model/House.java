package com.webpage.real_estate.model;

public class House extends Property {
    private int numOfBedrooms;
    private int numOfBathrooms;

    public House() {
    }

    public House(String title, String location, double landSize, double price, String description, String imagePath, int numOfBedrooms, int numOfBathrooms) {
        super(title, description, landSize, price, location, imagePath);
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
    }

    public House(String location, double size, double price, String description, String ownerName, String ownerContact, int numOfBedrooms, int numOfBathrooms, boolean isAvailable, String imagePath, String userId) {
        super(location, size, price, description, ownerName, isAvailable, imagePath, userId);
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        setPropertyType("House");
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

}
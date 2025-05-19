package com.webpage.real_estate.model;

public class Apartment extends Property {
    private int floorNumber;
    private int numberOfRooms;
    private boolean hasElevator;

    public Apartment(String title, String location, double landSize, double price, String description, String ownerName, String userId, int floorNumber, int numberOfRooms, boolean hasElevator) {
        super(title, description, landSize, price, location, ownerName, userId);
        this.floorNumber = floorNumber;
        this.numberOfRooms = numberOfRooms;
        this.hasElevator = hasElevator;
    }

    public Apartment(String title, String location, double landSize, double price, String description, String imagePath, int floorNumber, int numberOfRooms, boolean hasElevator) {
        super(title, description, landSize, price, location, imagePath);
        this.floorNumber = floorNumber;
        this.numberOfRooms = numberOfRooms;
        this.hasElevator = hasElevator;
    }

    public Apartment(){}

    public int getFloorNumber() {

        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {

        this.floorNumber = floorNumber;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {

        this.numberOfRooms = numberOfRooms;
    }

    public boolean hasElevator() {

        return hasElevator;
    }

    public void setHasElevator(boolean hasElevator) {

        this.hasElevator = hasElevator;
    }


}
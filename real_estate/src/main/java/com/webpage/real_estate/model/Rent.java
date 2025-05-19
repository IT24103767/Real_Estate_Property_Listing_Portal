package com.webpage.real_estate.model;

public class Rent extends Property {
    private double pricePerMonth;
    private double pricePerNight;
    private int minRentalDays;



    // Constructor
    public Rent(String location,
                double size, double price, String description,
                String ownerName, boolean isAvailable, String imagePath,String userId, double pricePerMonth, double pricePerNight, int minRentalDays) {

        // Call parent constructor
        super(location, size,price, description, ownerName, isAvailable, imagePath, userId);

        this.pricePerMonth = pricePerMonth;
        this.pricePerNight = pricePerNight;
        this.minRentalDays = minRentalDays;
    }

    public Rent(String title, String location, double landSize, double price, String description, String imagePath, double pricePerMonth, double pricePerNight, int minRentalDays) {
        super(title, description, landSize, price, location, imagePath);
        this.pricePerMonth = pricePerMonth;
        this.pricePerNight = pricePerNight;
        this.minRentalDays = minRentalDays;
    }

    public Rent() {}

    // Setters for new attributes
    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    public void setMinRentalDays(int minRentalDays) {
        this.minRentalDays = minRentalDays;
    }

    // Getters for new attributes
    public double getPricePerMonth() { return pricePerMonth; }
    public double getPricePerNight() { return pricePerNight; }
    public int getMinRentalDays() { return minRentalDays;}

}

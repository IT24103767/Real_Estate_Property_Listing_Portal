package com.webpage.real_estate.model;

public class Land extends Property {
    private String landType;
    private boolean hasUtilities;
    private boolean isFenced;


    public Land(String propertyId, String location, double landSize, double price, String description, String ownerName, String ownerContact, String landType, boolean hasUtilities, boolean isFenced) {
        super(propertyId, description, landSize, price, location, ownerName, ownerContact);
        this.landType = landType;
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
        super.setPropertyType("Land");
    }


    public Land(String propertyId, String location, double landSize, double price, String description, String ownerName, String ownerContact, String propertyType, String landType, boolean hasUtilities, boolean isFenced) {
        super(propertyId, description, landSize, price, location, ownerName, ownerContact, propertyType);
        this.landType = landType;
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
    }


    public Land(String title, String location, double landSize, double price, String description, String imagePath, boolean hasUtilities, boolean isFenced) {
        super(title, description, landSize, price, location, imagePath);
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public boolean hasUtilities() {
        return hasUtilities;
    }

    public void setHasUtilities(boolean hasUtilities) {
        this.hasUtilities = hasUtilities;
    }

    public boolean isFenced() {
        return isFenced;
    }

    public void setFenced(boolean fenced) {
        isFenced = fenced;
    }
}



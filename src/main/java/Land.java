public class Land extends Property {
    private String landType;
    private boolean hasUtilities;
    private boolean isFenced;


    public Land(String propertyId, String location, double landSize,
                double price, String description, String ownerName, String ownerContact,
                String landType, boolean hasUtilities, boolean isFenced) {
        super(propertyId, location, landSize, price, description, ownerName, ownerContact);
        this.landType = landType;
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
        super.setPropertyType("Land");  
    }


    public Land(String propertyId, String location, double landSize,
                double price, String description, String ownerName, String ownerContact,
                String propertyType, String landType, boolean hasUtilities, boolean isFenced) {
        super(propertyId, location, landSize, price, description, ownerName, ownerContact, propertyType);
        this.landType = landType;
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
    }


    public Land(String location, double landSize, double price, String description,
                String ownerName, String ownerContact, boolean isAvailable,
                String imagePath, String userId, String buyerId,
                String landType, boolean hasUtilities, boolean isFenced) {
        super(location, landSize, price, description, ownerName, ownerContact,
                isAvailable, imagePath, userId, buyerId);
        this.landType = landType;
        this.hasUtilities = hasUtilities;
        this.isFenced = isFenced;
        super.setPropertyType("Land");
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



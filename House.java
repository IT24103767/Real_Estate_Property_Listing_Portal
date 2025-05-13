import com.example.real_estate_property_listing_portal.Property;

class House extends Property {
    private int numOfBedrooms;
    private int numOfBathrooms;

    public House() {
        System.out.println("Constructing House");
    }

    public House(String propertyId, String location, double size, double price, String description, String ownerName, String ownerContact, int numOfBedrooms, int numOfBathrooms) {
        super(String propertyId, String location; double size, double price, String description, String ownerName, String ownerContact);
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

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Property Type: " + getPropertyType());
        System.out.println("Specifics: Number of Bedrooms" + numOfBedrooms + ", Number of Bathrooms" + numOfBathrooms);
    }

}


public class Property {

    private String propertyId;
    private String location;
    private double size;
    private double price;
    private String description;
    private String ownerName;
    private String ownerContact;
    private boolean isAvailable;
    private String propertyType;


    public Property(String propertyId, String location, double size,
                    double price, String description, String ownerName, String ownerContact) {
        this.propertyId = propertyId;
        this.location = location;
        this.size = size;
        this.price = price;
        this.description = description;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.isAvailable = true;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
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

    public void displayDetails() {
        System.out.println("Property ID: " + propertyId);
        System.out.println("Location: " + location);
        System.out.println("Price: $" + price);
        System.out.println("Description: " + description);
        System.out.println("Owner: " + ownerName);
        System.out.println("Contact: " + ownerContact);
        System.out.println("Status: " + (isAvailable ? "Available" : "Not Available"));
    }
}
public class Apartment extends Property {
    private int floorNumber;
    private int numberOfRooms;
    private boolean hasElevator;

    public Apartment(String propertyId, String location, double size,
                     double price, String description, String ownerName,
                     String ownerContact, int floorNumber, int numberOfRooms,
                     boolean hasElevator) {
        super(propertyId, location, size, price, description, ownerName, ownerContact);
        setPropertyType("Apartment");
        this.floorNumber = floorNumber;
        this.numberOfRooms = numberOfRooms;
        this.hasElevator = hasElevator;
    }

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

    @Override
    public void displayDetails() {
        System.out.println("\n==== Apartment Details ====");
        super.displayDetails();
        System.out.println("Size: " + getSize() + " sq.m");
        System.out.println("Floor Number: " + floorNumber);
        System.out.println("Number of Rooms: " + numberOfRooms);
        System.out.println("Has Elevator: " + (hasElevator ? "Yes" : "No"));
    }
}

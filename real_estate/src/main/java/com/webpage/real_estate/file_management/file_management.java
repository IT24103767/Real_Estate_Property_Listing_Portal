package com.webpage.real_estate.file_management;

import com.webpage.real_estate.model.*;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class file_management {
    private static final String user_file = "users.txt";
    private static final String property_file = "properties.txt";
    private static final String house_file = "houses.txt";
    private static final String apartment_file = "apartment.txt";
    private static final String rent_file = "rent.txt";
    private static final String land_file = "land.txt";

    static {
        try {
            new File(user_file).createNewFile();
            new File(property_file).createNewFile();
            new File(house_file).createNewFile();
            new File(apartment_file).createNewFile();
            new File(rent_file).createNewFile();
            new File(land_file).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(user_file, true))) {
            bw.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getPhone());
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean Email(User user) {
        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.split(",")[1].equals(user.getEmail())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateUser(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(email) && data[2].equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User readUser(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(email) && validateUser(email, password)) {
                    User user = new User();
                    user.setUsername(data[0]);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setPhone(data[data.length - 1]);
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User getUserByEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(email)) {
                    User user = new User();
                    user.setUsername(data[0]);
                    user.setEmail(data[1]);
                    user.setPassword(data[2]);
                    user.setPhone(data[3]);
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateUser(User user) {
        List<String> users = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(user.getEmail())) {
                    users.add(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getPhone());
                    found = true;
                } else {
                    users.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (!found) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(user_file))) {
            for (String userLine : users) {
                bw.write(userLine);
                bw.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void writeProperty(Property property, HttpSession session, String property_type) {
        String id = generatePropertyId();
        String email = (String) session.getAttribute("email");
        String file;

        if (property_type.equalsIgnoreCase("house")) {
            file = house_file;
        } else if (property_type.equalsIgnoreCase("apartment")) {
            file = apartment_file;
        } else if (property_type.equalsIgnoreCase("rent")) {
            file = rent_file;
        } else {
            file = land_file;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            StringBuilder str = new StringBuilder();
            str.append(id).append(",")
               .append(property_type).append(",")
               .append(email).append(",")
               .append(property.getTitle()).append(",")
               .append(property.getLandSize()).append(",")
               .append(property.getPrice()).append(",")
               .append(property.getLocation()).append(",")
               .append(property.getDescription()).append(",")
               .append(property.getImagePath() != null ? property.getImagePath() : "").append(",");

            switch (property_type.toLowerCase()) {
                case "house":
                    if (property instanceof House) {
                        House house = (House) property;
                        str.append(house.getNumOfBedrooms()).append(",")
                           .append(house.getNumOfBathrooms());
                    }
                    break;
                case "apartment":
                    if (property instanceof Apartment) {
                        Apartment apt = (Apartment) property;
                        str.append(apt.getFloorNumber()).append(",")
                           .append(apt.getNumberOfRooms()).append(",")
                           .append(apt.hasElevator());
                    }
                    break;
                case "rent":
                    if (property instanceof Rent) {
                        Rent rent = (Rent) property;
                        str.append(rent.getPricePerMonth()).append(",")
                           .append(rent.getPricePerNight()).append(",")
                           .append(rent.getMinRentalDays());
                    }
                    break;
                default:
                    if (property instanceof Land) {
                        Land land = (Land) property;
                        str.append(land.hasUtilities()).append(",")
                           .append(land.isFenced());
                    }
                    break;
            }

            bw.write(str.toString());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateProperty(String propertyId, Property property, HttpSession session, String property_type) {
        String email = (String) session.getAttribute("email");
        String file;

        switch (property_type.toLowerCase()) {
            case "house":
                file = house_file;
                break;
            case "apartment":
                file = apartment_file;
                break;
            case "rent":
                file = rent_file;
                break;
            default:
                file = land_file;
                break;
        }

        List<String> properties = getPropertiesFromFile(file);
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String prop : properties) {
            String[] parts = prop.split(",");
            if (parts[0].equals(propertyId) && parts[2].equals(email)) {
                found = true;
                StringBuilder str = new StringBuilder();
                str.append(propertyId).append(",")
                   .append(property_type).append(",")
                   .append(email).append(",")
                   .append(property.getTitle()).append(",")
                   .append(property.getLandSize()).append(",")
                   .append(property.getPrice()).append(",")
                   .append(property.getLocation()).append(",")
                   .append(property.getDescription()).append(",")
                   .append(property.getImagePath() != null ? property.getImagePath() : "").append(",");

                switch (property_type.toLowerCase()) {
                    case "house":
                        if (property instanceof House) {
                            House h = (House) property;
                            str.append(h.getNumOfBedrooms()).append(",")
                               .append(h.getNumOfBathrooms());
                        }
                        break;
                    case "apartment":
                        if (property instanceof Apartment) {
                            Apartment apt = (Apartment) property;
                            str.append(apt.getFloorNumber()).append(",")
                               .append(apt.getNumberOfRooms()).append(",")
                               .append(apt.hasElevator());
                        }
                        break;
                    case "rent":
                        if (property instanceof Rent) {
                            Rent r = (Rent) property;
                            str.append(r.getPricePerMonth()).append(",")
                               .append(r.getPricePerNight()).append(",")
                               .append(r.getMinRentalDays());
                        }
                        break;
                    default:
                        if (property instanceof Land) {
                            Land l = (Land) property;
                            str.append(l.hasUtilities()).append(",")
                               .append(l.isFenced());
                        }
                        break;
                }
                updated.add(str.toString());
            } else {
                updated.add(prop);
            }
        }

        if (!found) {
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : updated) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPropertiesFromFile(String filePath) {
        List<String> properties = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                properties.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static List<String> getPropertiesForEmail(String email) {
        List<String> properties = new ArrayList<>();
        String[] files = {house_file, apartment_file, rent_file, land_file};
        
        for (String file : files) {
            File f = new File(file);
            if (!f.exists()) {
                continue;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 2 && parts[2].equalsIgnoreCase(email)) {
                        properties.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static String generatePropertyId() {
        return UUID.randomUUID().toString();
    }

    public static boolean deleteProperty(String propertyId, HttpSession session) {
        String email = (String) session.getAttribute("email");
        String[] files = {house_file, apartment_file, rent_file, land_file};
        boolean deleted = false;

        for (String file : files) {
            List<String> properties = getPropertiesFromFile(file);
            List<String> updated = new ArrayList<>();
            boolean foundInFile = false;

            for (String prop : properties) {
                String[] parts = prop.split(",");
                if (parts.length > 2 && parts[0].equals(propertyId) && parts[2].equals(email)) {
                    foundInFile = true;
                    deleted = true;
                    continue;
                }
                updated.add(prop);
            }

            if (foundInFile) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (String line : updated) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                break;
            }
        }

        if (deleted) {
            writeProperties();
        }

        return deleted;
    }

    public static boolean deleteUser(String email) {
        List<String> users = new ArrayList<>();
        boolean deleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(user_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[1].equals(email)) {
                    users.add(line);
                } else {
                    deleted = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(user_file))) {
            for (String userLine : users) {
                bw.write(userLine);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        List<String> properties = getProperties();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(property_file))) {
            for (String prop : properties) {
                String[] parts = prop.split(",");
                if (!parts[1].equals(email)) {
                    bw.write(prop);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return deleted;
    }

    private static void writeProperties() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(property_file))) {
            String[] files = {house_file, apartment_file, rent_file, land_file};
            for (String file : files) {
                File f = new File(file);
                if (!f.exists()) {
                    continue;
                }
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getProperties() {
        List<String> properties = new ArrayList<>();
        writeProperties();
        
        File propFile = new File(property_file);
        if (!propFile.exists()) {
            return properties;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(property_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                properties.add(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}

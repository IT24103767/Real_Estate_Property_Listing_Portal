public class QuickSortProperties {
    public static void quickSort(Property[] properties, int low, int high) {
        if (low < high) {
            int pi = partition(properties, low, high);
            quickSort(properties, low, pi - 1);
            quickSort(properties, pi + 1, high);
        }
    }

    private static int partition(Property[] properties, int low, int high) {
        double pivot = properties[high].getPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (properties[j].getPrice() <= pivot) {
                i++;
                swap(properties, i, j);
            }
        }
        swap(properties, i + 1, high);
        return i + 1;
    }

    private static void swap(Property[] properties, int i, int j) {
        Property temp = properties[i];
        properties[i] = properties[j];
        properties[j] = temp;
    }

    public static void main(String[] args) {
        Property[] properties = {
                new Property("H001", 350000.0, "456/2 Koswatte St."),
                new Property("H002", 450000.0, "809/2 Pugoda St."),
                new Property("H003", 350000.0, "10/7 Colombo St."),
                new Property("H004", 550000.0, "105/3 Malabe St.")
        };

        System.out.println("Before sorting:");
        for (Property p : properties) {
            System.out.println(p);
        }

        quickSort(properties, 0, properties.length - 1);

        System.out.println("\nAfter sorting by price:");
        for (Property p : properties) {
            System.out.println(p);
        }
    }
}

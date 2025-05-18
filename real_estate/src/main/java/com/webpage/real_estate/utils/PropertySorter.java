package com.webpage.real_estate.utils;

import java.util.List;

public class PropertySorter {

    public static void quickSort(List<String> properties, String order) {
        if (properties == null || properties.size() <= 1) {
            return;
        }
        quickSort(properties, 0, properties.size() - 1, order);
    }

    private static void quickSort(List<String> properties, int low, int high, String order) {
        if (low < high) {
            int pivotIndex = partition(properties, low, high, order);
            quickSort(properties, low, pivotIndex - 1, order);
            quickSort(properties, pivotIndex + 1, high, order);
        }
    }

    private static int partition(List<String> properties, int low, int high, String order) {
        String pivot = properties.get(high);
        double pivotPrice = extractPrice(pivot);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            double currentPrice = extractPrice(properties.get(j));

            if (order.equals("asc") && currentPrice <= pivotPrice ||
                    order.equals("desc") && currentPrice >= pivotPrice) {
                i++;
                swap(properties, i, j);
            }
        }

        swap(properties, i + 1, high);
        return i + 1;
    }

    private static void swap(List<String> properties, int i, int j) {
        String temp = properties.get(i);
        properties.set(i, properties.get(j));
        properties.set(j, temp);
    }

    private static double extractPrice(String property) {
        try {
            String[] parts = property.split(",");
            // For rent properties, use the monthly rent price
            if (parts[1].equals("rent")) {
                return Double.parseDouble(parts[9].trim()); // Monthly price
            } else {
                return Double.parseDouble(parts[5].trim()); // Regular price
            }
        } catch (Exception e) {
            return 0.0; // Return 0 if price cannot be extracted
        }
    }
} 
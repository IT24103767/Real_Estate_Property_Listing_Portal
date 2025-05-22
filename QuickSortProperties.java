package com.webpage.real_estate.utils;

import com.webpage.real_estate.model.Property;

import java.util.List;

public class QuickSortProperties {
    public static void quickSort(List<String> properties, int low, int high) {
        if (low < high) {
            int pi = partition(properties, low, high);
            quickSort(properties, low, pi - 1);
            quickSort(properties, pi + 1, high);
        }
    }

    private static int partition(List<String> properties, int low, int high) {
        String[] line = (properties.get(high)).split(",");
        String[] newline;
        double pivot = Double.parseDouble(line[1].equals("rent") ? line[9].trim() : line[5].trim());
        int i = low - 1;
        for (int j = low; j < high; j++) {
            newline = (properties.get(j)).split(",");
            if (Double.parseDouble(newline[1].equals("rent") ? newline[9].trim() : newline[5].trim()) <= pivot) {
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
        properties.set(j, String.valueOf(temp));
    }
}

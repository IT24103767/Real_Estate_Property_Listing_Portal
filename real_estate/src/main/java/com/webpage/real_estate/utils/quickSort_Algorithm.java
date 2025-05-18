//package com.webpage.real_estate.utils;
//
//import java.util.List;
//
//public class QuickSortProperties {
//    public static void quickSort(List<String> properties, int low, int high) {
//        if (low < high) {
//            int pi = partition(properties, low, high);
//            quickSort(properties, low, pi - 1);
//            quickSort(properties, pi + 1, high);
//        }
//    }
//
//    private static int partition(List<String> properties, int low, int high) {
//        double pivot = properties.get(high).;
//        int i = low - 1;
//        for (int j = low; j < high; j++) {
//            if (properties[j].getPrice() <= pivot) {
//                i++;
//                swap(properties, i, j);
//            }
//        }
//        swap(properties, i + 1, high);
//        return i + 1;
//    }
//
//    private static void swap(List<String> properties, int i, int j) {
//        String temp = properties[i];
//        properties[i] = properties[j];
//        properties[j] = temp;
//    }
//}

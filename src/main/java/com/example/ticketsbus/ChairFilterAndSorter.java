package com.example.ticketsbus;
import javafx.beans.property.StringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

public class ChairFilterAndSorter {
    public static void filterAndSortAsync(FilteredList<Chair> filteredData, StringProperty searchProperty, TableView<Chair> tableSeats) {
        Thread filterAndSortThread = new Thread(() -> {
            filterAndSort(filteredData, searchProperty, tableSeats);
        });

        filterAndSortThread.setDaemon(true); // Set the thread as a daemon thread
        filterAndSortThread.start();
    }

    public static void filterAndSort(FilteredList<Chair> filteredData, StringProperty searchProperty, TableView<Chair> tableSeats) {
        searchProperty.addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(chair -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (chair.getSeatName().toString().toLowerCase().contains(searchKeyword) ||
                        chair.getPicked().toString().toLowerCase().contains(searchKeyword) ||
                        chair.getService().toString().toLowerCase().contains(searchKeyword) ||
                        chair.getUname().toString().toLowerCase().contains(searchKeyword)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Chair> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableSeats.comparatorProperty());
        tableSeats.setItems(sortedData);
    }
}

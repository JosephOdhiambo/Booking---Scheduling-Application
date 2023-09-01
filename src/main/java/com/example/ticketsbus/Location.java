package com.example.ticketsbus;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Location {
    private StringProperty location;
    public Location(String location){
        this.location = new SimpleStringProperty(location);
    }

    public StringProperty getLocation(){
        return location;
    }
}

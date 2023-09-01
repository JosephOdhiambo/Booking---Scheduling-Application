package com.example.ticketsbus;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class reportAvailableSeats {
    private StringProperty service;
    private ObjectProperty<Integer> seats;


    public reportAvailableSeats(String service,Integer seats){
        this.service = new SimpleStringProperty(service);
        this.seats = new SimpleObjectProperty<>(seats);
    }

    public StringProperty getService(){
        return service;
    }

    public ObjectProperty<Integer> getSeats(){
        return seats;
    }
}

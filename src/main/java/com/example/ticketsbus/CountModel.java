package com.example.ticketsbus;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CountModel {
    private StringProperty servicE;
    private ObjectProperty<Integer> seatscount;
    private ObjectProperty<Integer> availableSeats;
    private ObjectProperty<Integer> difference;

    public CountModel(String servicE, Integer seatscount, Integer availableSeats, Integer difference){
        this.servicE = new SimpleStringProperty(servicE);
        this.seatscount = new SimpleObjectProperty<>(seatscount);
        this.availableSeats= new SimpleObjectProperty<>(availableSeats);
        this.difference = new SimpleObjectProperty<>(difference);
    }

    public StringProperty getServicE(){
        return servicE;
    }

    public ObjectProperty<Integer> getSeatscount(){
        return seatscount;
    }

    public ObjectProperty<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public ObjectProperty<Integer> getDifference(){
        return difference;
    }
}

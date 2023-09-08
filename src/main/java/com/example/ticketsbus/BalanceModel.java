package com.example.ticketsbus;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;

public class BalanceModel {
    private StringProperty username;
    private ObjectProperty<Integer> balance;

    public BalanceModel(String username, Integer balance){
        this.username = new SimpleStringProperty(username);
        this.balance = new SimpleObjectProperty<>(balance);
    }

    public StringProperty getUsername(){return username;}
    public ObjectProperty<Integer> getBalance(){return balance;}
}

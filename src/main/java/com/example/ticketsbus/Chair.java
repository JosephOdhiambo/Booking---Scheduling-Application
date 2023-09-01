package com.example.ticketsbus;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

public class Chair{
private StringProperty seatname;
private StringProperty picked;
private StringProperty service;
private StringProperty uname;

public Chair(String seatname, String picked, String service, String uname){
    this.seatname = new SimpleStringProperty(seatname);
    this.picked = new SimpleStringProperty(picked);
    this.service = new SimpleStringProperty(service);
    this.uname = new SimpleStringProperty(uname);
}
public StringProperty getSeatName(){
    return seatname;
}
public StringProperty getPicked(){
    return picked;
}
public StringProperty getService(){
    return service;
}
public StringProperty getUname(){
    return uname;
}

    public void setPicked(String picked) {
        this.picked.set(picked);
    }

    public void setSeatname(String seatname) {
        this.seatname.set(seatname);
    }

    public void setService(String service) {
        this.service.set(service);
    }

    public void setUname(String uname) {
        this.uname.set(uname);
    }
}

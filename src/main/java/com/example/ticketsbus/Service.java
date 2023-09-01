package com.example.ticketsbus;

import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalTime;

public class Service {

    private StringProperty service;
    private StringProperty source;
    private StringProperty destination;
    private StringProperty fare;
    private ObjectProperty<LocalTime> dtime;
    private ObjectProperty<LocalTime> atime;
    private ObjectProperty<Integer> seats;
    private StringProperty dt;

    private StringProperty iframe;

    public Service(String service, String source, String destination, String fare, LocalTime dtime, LocalTime atime, Integer seats, String dt, String iframe) {
        this.service = new SimpleStringProperty(service);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.fare = new SimpleStringProperty(fare);
        this.dtime = new SimpleObjectProperty<>(dtime);
        this.atime = new SimpleObjectProperty<>(atime);
        this.seats = new SimpleObjectProperty<>(seats);
        this.dt = new SimpleStringProperty(dt);
        this.iframe = new SimpleStringProperty(iframe);
    }
    public Service(String service, String source, String destination, String fare, LocalTime dtime, LocalTime atime, String dt) {
        this.service = new SimpleStringProperty(service);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.fare = new SimpleStringProperty(fare);
        this.dtime = new SimpleObjectProperty<>(dtime);
        this.atime = new SimpleObjectProperty<>(atime);
        this.dt = new SimpleStringProperty(dt);
    }

    public StringProperty getService() {
        return service;
    }

    public void setService(StringProperty service) {
        this.service = service;
    }

    public StringProperty getSource() {
        return source;
    }

    public void setSource(StringProperty source) {
        this.source = source;
    }

    public StringProperty getDestination() {
        return destination;
    }

    public void setDestination(StringProperty destination) {
        this.destination = destination;
    }

    public StringProperty getFare() {
        return fare;
    }

    public void setFare(StringProperty fare) {
        this.fare = fare;
    }

    public ObjectProperty<LocalTime> getDtime() {
        return dtime;
    }

    public void setDtime(ObjectProperty<LocalTime> dtime) {
        this.dtime = dtime;
    }

    public ObjectProperty<LocalTime> getAtime() {
        return atime;
    }

    public void setAtime(ObjectProperty<LocalTime> atime) {
        this.atime = atime;
    }

    public ObjectProperty<Integer> getSeats() {
        return seats;
    }

    public void setSeats(ObjectProperty<Integer> seats) {
        this.seats = seats;
    }

    public StringProperty getDt() {
        return dt;
    }

    public void setDt(StringProperty dt) {
        this.dt = dt;
    }

    public StringProperty getIframe(){return iframe; }

}
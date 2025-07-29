package com.irctc.booking.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Integer>> seats;
    private Map<String, Time> trainShedule;
    private List<String> station;

    public Train() {
    }

    public Train(String trainId, String trainNumber, List<List<Integer>> seats, Map<String, Time> trainShedule, List<String> station) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.seats = seats;
        this.trainShedule = trainShedule;
        this.station = station;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public Map<String, Time> getTrainShedule() {
        return trainShedule;
    }

    public void setTrainShedule(Map<String, Time> trainShedule) {
        this.trainShedule = trainShedule;
    }

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }
}

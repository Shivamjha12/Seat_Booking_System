package com.irctc.booking.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Integer>> seats;
    private Map<String, String> trainShedule;
    private List<String> station;

    public Train() {
    }

    public Train(String trainId, String trainNumber, List<List<Integer>> seats, Map<String, String> trainShedule, List<String> station) {
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

    public Map<String, String> getTrainShedule() {
        return trainShedule;
    }

    public void setTrainShedule(Map<String, String> trainShedule) {
        this.trainShedule = trainShedule;
    }

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s",trainId,trainNumber);
    }
}


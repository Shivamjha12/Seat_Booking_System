package com.irctc.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {

    @JsonProperty("train_id")
    private String trainId;

    @JsonProperty("train_no")
    private String trainNumber;

    @JsonProperty("seats")
    private List<List<Integer>> seats;

    @JsonProperty("station_times")
    private Map<String, String> trainShedule;

    @JsonProperty("stations")
    private List<String> station;

    public Train() {
    }

    public Train(String trainId, String trainNumber, List<List<Integer>> seats,
                 Map<String, String> trainShedule, List<String> station) {
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
    @JsonIgnore
    private Integer getCurrentSeatCapacity(){
        Integer availableSeats = 0;
        for(int i=0;i<seats.size();i++){
            for(int j=0;j<seats.get(i).size();j++){
                if(seats.get(i).get(j)==0){
                    availableSeats+=1;
                }
            }
        }
        return availableSeats;
    }

    @JsonIgnore
    public String getTrainInfo() {
        return String.format("Train ID: %s Train No: %s with Capacity: %d out of %d", trainId, trainNumber,getCurrentSeatCapacity(),seats.get(0).size()*seats.size());
    }
}

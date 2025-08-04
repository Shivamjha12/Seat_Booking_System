package com.irctc.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irctc.booking.entities.Train;
import com.irctc.booking.entities.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class TrainService {
    Train train;
    List<Train> trainList;
    private ObjectMapper objectMapper= new ObjectMapper();

    private static final String TRAIN_PATH="app/src/main/resources/trains.json";

    private List<Train> loadTrains() throws IOException{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("trains.json");
        if (inputStream == null) {
            throw new FileNotFoundException("train.json not found in resources folder");
        }
        try {
            trainList = objectMapper.readValue(inputStream, new TypeReference<List<Train>>() {});
        } catch (IOException e) {
            System.out.println("Something Went Wrong: IOException Occured In Code>>>>>>>>>>>>>");
            e.printStackTrace();  // Print full error details
            throw e;  // re-throw or handle accordingly
        }
        return trainList;
    }
    public TrainService() throws IOException{
        loadTrains();
    }
    public Optional<List<Train>> getTrainList(){
        return Optional.ofNullable(trainList);
    }
}

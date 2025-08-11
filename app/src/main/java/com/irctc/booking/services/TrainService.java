package com.irctc.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irctc.booking.entities.Train;
import com.irctc.booking.entities.User;

import java.io.File;
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

    private void saveTrainListToFile() throws IOException{
        File trainFile = new File(TRAIN_PATH);
        objectMapper.writeValue(trainFile,trainList);
    }


    public Optional<List<Train>> getTrainList(){
        return Optional.ofNullable(trainList);
    }
    public Optional<Train> getTrainObjectById(String trainIdPassed){

        try{
            loadTrains();
        } catch (IOException e){
            System.out.println("Get Error While Updating the train list");
        }
        Optional<Train> trainObjectbyId = trainList.stream().
                filter(
                        train -> train.getTrainId().equals(trainIdPassed))
                .findFirst();
        return  trainObjectbyId;
    }
    public void updateTrainInformation(Train newTrainObject){
        for(int i=0;i<trainList.size();i++){
            Train currentTrainInLoop = trainList.get(i);
            if(currentTrainInLoop.getTrainId().equals(newTrainObject.getTrainId())){
                trainList.set(i,newTrainObject);
                try{
                    saveTrainListToFile();
                    System.out.println("Train Listed is Updated");
                    break;
                }catch (IOException e){
                    System.out.println("Caught Error While Saving Updated Train List to train.json");
                }
                break;
            }
        }
        System.out.println("Train not Found in List");

    }


}

package com.irctc.booking.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irctc.booking.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.irctc.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();


    private static final String USER_PATH="app/src/main/java/com/irctc/booking/localDb/users.json";


    public UserBookingService(User userPassed ) throws IOException {
    this.user = userPassed;
    File users = new File(USER_PATH);
    userList = objectMapper.readValue(users, new TypeReference<List<User>>(){});


    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File userFile = new File(USER_PATH);
        objectMapper.writeValue(userFile,userList);
    }



}

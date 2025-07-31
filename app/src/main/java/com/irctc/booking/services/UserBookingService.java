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

    public List<User> loadUsers() throws  IOException{
        File users = new File(USER_PATH);
        return userList = objectMapper.readValue(users, new TypeReference<List<User>>(){});

    }
    public UserBookingService() throws IOException{
        loadUsers();
    }



    public UserBookingService(User userPassed ) throws IOException {
    this.user = userPassed;
    loadUsers();


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

    public void fetchBooking(){
        user.printTickets();

    }

    public Optional<User> returnUserByEmailPassword(String emailToFind,String passwordToFind){
        Optional<User> currentUser = userList.stream().filter(
                user ->user.getEmail().equalsIgnoreCase(emailToFind)
                &&
                        user.getPassword().equals(passwordToFind)
                )
                .findFirst();
        return currentUser;
    }

    public Boolean isUserPresentInDb(String emailToFind){
        for(User user : userList){
            if(user.getEmail().equalsIgnoreCase(emailToFind)  ){
                return true;
            }
        }
        return  false;
    }
    public boolean cancelBooking(String ticketId){
        return Boolean.FALSE;
    }



}

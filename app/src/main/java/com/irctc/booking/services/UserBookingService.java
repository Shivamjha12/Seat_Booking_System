package com.irctc.booking.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irctc.booking.entities.Ticket;
import com.irctc.booking.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.irctc.booking.util.UserServiceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();


    private static final String USER_PATH="app/src/main/resources/users.json";

    public List<User> loadUsers() throws  IOException{
//        System.out.println("File Path-----------------------------------------------------------");
//        System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());
//        System.out.println("Resolved File Path: " + new File(USER_PATH).getAbsolutePath());
//        System.out.println("File Path-----------------------------------------------------------");

//        System.out.println("Getting Error Here----- Line 1 loadUsers() function");
//        File users = new File(USER_PATH);
//        System.out.println("Getting Error Here----- Line 2 loadUsers() function");
//        return userList = objectMapper.readValue(users, new TypeReference<List<User>>(){});

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("users.json");

        if (inputStream == null) {
            throw new FileNotFoundException("users.json not found in resources folder");
        }

        System.out.println("Getting Error Here----- Line 2 loadUsers() function");

        try {
            userList = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.out.println("Something Went Wrong: IOException Occured In Code>>>>>>>>>>>>>");
            e.printStackTrace();  // Print full error details
            throw e;  // re-throw or handle accordingly
        }
        return  userList;

    }


    public UserBookingService() throws IOException{
//        System.out.println("Getting Error Here----- Line 1 UserBookingService() function");
        loadUsers();
//        System.out.println("Getting Error Here----- Line 2 UserBookingService() function");
    }

    public Optional<List<Ticket>> getCurrentUserTickets(){
        List<Ticket> currentUserTickets = user.getTicketsBooked();
        return Optional.ofNullable(currentUserTickets);
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
        currentUser.ifPresent(value -> user = value);
        return currentUser;
    }
    public Optional<User> getUser(){
        return Optional.ofNullable(user);
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

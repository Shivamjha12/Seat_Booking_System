package com.irctc.booking.entities;

import java.util.List;

public class User {

    private String name;
    private Integer age;
    private String email;
    private String password;
    private String hashedPassword;
    private String city;
    private String userId;
    private List<Ticket> ticketsBooked;

    public User(String name, String email,String userId, Integer age,String city,String password,String hashedPassword,List<Ticket> ticketsBooked) {
        this.userId = userId;
        this.name = name;
        this.email=email;
        this.age = age;
        this.city = city;
        this.password = password;
        this.hashedPassword=hashedPassword;
        this.ticketsBooked=ticketsBooked;
    }

    //  We created this default constructor if user doesn't pass anything
    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAge(String email) {
        this.email = email;
    }
    public String getEmail(){ return email; }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Ticket> getTicketBooked() {
        return ticketsBooked;
    }

    public void setTicketBooked(List<Ticket> ticketBooked) {
        this.ticketsBooked = ticketBooked;
    }

    public void printTickets(){
        for(int i=0;i<ticketsBooked.size();i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }


}

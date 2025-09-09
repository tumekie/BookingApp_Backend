package com.example.booking.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDTO {
    private LocalDate date;
    private String time;
    private String name;
    private String email;
    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }
}

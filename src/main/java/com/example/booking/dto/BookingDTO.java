package com.example.booking.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDTO {
    private LocalDate date;
    private LocalTime time;
    private String name;
    private String email;
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
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

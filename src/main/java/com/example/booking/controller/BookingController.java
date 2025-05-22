package com.example.booking.controller;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.saveBooking(bookingDTO);
    }


//    @GetMapping("/user/{userId}")
//    public List<Booking> getUserBookings(@PathVariable Long userId) {
//        return bookingService.getUserBookings(userId);
//    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/times")
    public ResponseEntity<List<String>> getBookedTimes(@RequestParam String date) {
        List<String> bookedTimes = bookingService.getBookedTimesByDate(date);
        return ResponseEntity.ok(bookedTimes);
    }
}

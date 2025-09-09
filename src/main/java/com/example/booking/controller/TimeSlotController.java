package com.example.booking.controller;

import com.example.booking.entity.TimeSlot;
import com.example.booking.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/available")
    public List<TimeSlot> getAvailableTimeSlots(@RequestParam("date") String date) {
        return timeSlotService.getAvailableTimeSlots(LocalDate.parse(date));
    }

    @PostMapping
    public TimeSlot addTimeSlot(@RequestBody TimeSlot slot) {
        return timeSlotService.addTimeSlot(slot);
    }
}

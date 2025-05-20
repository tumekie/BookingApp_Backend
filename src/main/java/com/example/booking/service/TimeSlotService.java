package com.example.booking.service;

import com.example.booking.entity.TimeSlot;
import com.example.booking.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepo;

    public List<TimeSlot> getAvailableTimeSlots(LocalDate date) {
        return timeSlotRepo.findByDateAndIsAvailable(date, true);
    }

    public TimeSlot addTimeSlot(TimeSlot slot) {
        return timeSlotRepo.save(slot);
    }
}

package com.example.booking.repository;

import com.example.booking.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDateAndIsAvailable(LocalDate date, boolean isAvailable);
}
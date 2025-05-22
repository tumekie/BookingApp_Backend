package com.example.booking.repository;

import com.example.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    //List<Booking> findByUserId(Long userId);
    List<Booking> findByDate(LocalDate date);
}
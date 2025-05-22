package com.example.booking.service;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.service.EmailService;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmailService emailService;

    public Booking saveBooking(BookingDTO bookingDTO) {
//        User user = userRepo.findById(bookingDTO.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setDate(bookingDTO.getDate());
        booking.setTime(bookingDTO.getTime());
        booking.setName(bookingDTO.getName());
        booking.setEmail(bookingDTO.getEmail());

        Booking saved = bookingRepo.save(booking);

        // Send confirmation email
        String email = saved.getEmail(); // make sure User has an email field
        String subject = "Booking Confirmation";
        String body = "Dear " + saved.getName() +
                ", your booking on " + saved.getDate() +
                " at " + saved.getTime() + " has been confirmed.";
        emailService.sendBookingConfirmation(email, subject, body);

        return saved;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

//    public List<Booking> getUserBookings(Long userId) {
//        return bookingRepo.findByUserId(userId);
//    }
    public List<String> getBookedTimesByDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString); // expects yyyy-MM-dd
        List<Booking> bookings = bookingRepo.findByDate(date);
        return bookings.stream()
                .map(Booking::getTime)
                .collect(Collectors.toList());
    }
}

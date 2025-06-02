package com.example.booking.service;

import com.example.booking.entity.Booking;
import com.example.booking.repository.BookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public ReminderService(BookingRepository bookingRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 9 * * *") // Every day at 9:00 AM server time
    public void sendReminderEmails() {
        System.out.println("Scheduled Daily Appointment Reminder Emails: " + LocalDateTime.now());
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Booking> bookingsForTomorrow = bookingRepository.findByDate(tomorrow);

        for (Booking booking : bookingsForTomorrow) {
            String email = booking.getEmail();
            String subject = "Appointment Reminder";
            String body = "Dear " + booking.getName() +
                    ", This is a reminder that you have an appointment on " + booking.getDate()+
                    " at " + booking.getTime() + " Thank you!";
            emailService.sendBookingConfirmation(email, subject, body);
        }
    }
}

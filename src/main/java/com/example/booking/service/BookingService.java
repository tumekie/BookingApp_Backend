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
import java.time.format.DateTimeFormatter;
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
        // User user = userRepo.findById(bookingDTO.getUserId())
        // .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setDate(bookingDTO.getDate());
        booking.setTime(bookingDTO.getTime());
        booking.setName(bookingDTO.getName());
        booking.setEmail(bookingDTO.getEmail());

        Booking saved = bookingRepo.save(booking);

        // Send confirmation email
        String email = saved.getEmail(); // make sure User has an email field
        String subject = "Booking Confirmation";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        String body = """
                <html>
                <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                    <h2 style="color: #2c3e50;">Booking Confirmation</h2>
                    <p>Dear <strong>%s</strong>,</p>
                    <p>
                    Thank you for your booking. We’re happy to confirm your reservation:
                    </p>
                    <table style="border-collapse: collapse; margin: 15px 0;">
                    <tr>
                        <td style="padding: 8px; border: 1px solid #ddd;"><strong>Date</strong></td>
                        <td style="padding: 8px; border: 1px solid #ddd;">%s</td>
                    </tr>
                    <tr>
                        <td style="padding: 8px; border: 1px solid #ddd;"><strong>Time</strong></td>
                        <td style="padding: 8px; border: 1px solid #ddd;">%s</td>
                    </tr>
                    </table>
                    <p>
                    We look forward to seeing you!
                    </p>
                    <p style="margin-top: 30px; font-size: 0.9em; color: #888;">
                    — The Booking Team
                    </p>
                </body>
                </html>
                """.formatted(saved.getName(), saved.getDate().format(formatter), saved.getTime());

        emailService.sendBookingConfirmation(email, subject, body);

        return saved;
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    // public List<Booking> getUserBookings(Long userId) {
    // return bookingRepo.findByUserId(userId);
    // }
    public List<String> getBookedTimesByDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString); // expects yyyy-MM-dd
        List<Booking> bookings = bookingRepo.findByDate(date);
        return bookings.stream()
                .map(Booking::getTime)
                .collect(Collectors.toList());
    }
}

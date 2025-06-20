package it.epicode.S7L5_ProgettoSettimanale.controller;

import it.epicode.S7L5_ProgettoSettimanale.dto.BookingResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{eventId}")
    public ResponseEntity<BookingResponseDto> bookEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(201).body(bookingService.bookEvent(eventId, user));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getUserBookings(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(user));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable Long bookingId,
            @AuthenticationPrincipal User user) {
        bookingService.cancelBooking(bookingId, user);
        return ResponseEntity.noContent().build();
    }
}

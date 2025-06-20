package it.epicode.S7L5_ProgettoSettimanale.service;

import it.epicode.S7L5_ProgettoSettimanale.dto.BookingResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.exception.BadRequestException;
import it.epicode.S7L5_ProgettoSettimanale.exception.NotFoundException;
import it.epicode.S7L5_ProgettoSettimanale.model.Booking;
import it.epicode.S7L5_ProgettoSettimanale.model.Event;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.repository.BookingRepository;
import it.epicode.S7L5_ProgettoSettimanale.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;
    private final EventRepository eventRepo;

    public BookingResponseDto bookEvent(Long eventId, User user) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));

        if (event.getAvailableSeats() <= 0) {
            throw new BadRequestException("Posti esauriti");
        }

        if (bookingRepo.existsByUserAndEvent(user, event)) {
            throw new BadRequestException("Hai già prenotato questo evento");
        }

        event.setAvailableSeats(event.getAvailableSeats() - 1);

        Booking booking = Booking.builder()
                .event(event)
                .user(user)
                .build();

        bookingRepo.save(booking);
        return mapToDto(booking);
    }

    public List<BookingResponseDto> getBookingsByUser(User user) {
        return bookingRepo.findByUser(user)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public void cancelBooking(Long bookingId, User user) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Non puoi annullare prenotazioni altrui");
        }

        Event event = booking.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        bookingRepo.delete(booking);
    }

    private BookingResponseDto mapToDto(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .eventId(booking.getEvent().getId())
                .eventTitle(booking.getEvent().getTitle())
                .userEmail(booking.getUser().getEmail())
                .build();
    }
}

package it.epicode.S7L5_ProgettoSettimanale.repository;

import it.epicode.S7L5_ProgettoSettimanale.model.Booking;
import it.epicode.S7L5_ProgettoSettimanale.model.Event;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    boolean existsByUserAndEvent(User user, Event event);
}

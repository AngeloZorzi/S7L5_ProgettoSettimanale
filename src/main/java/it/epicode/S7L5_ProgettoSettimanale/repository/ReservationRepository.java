package it.epicode.S7L5_ProgettoSettimanale.repository;

import it.epicode.S7L5_ProgettoSettimanale.model.Event;
import it.epicode.S7L5_ProgettoSettimanale.model.Reservation;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    Optional<Reservation> findByUserAndEvent(User user, Event event);
    int countByEvent(Event event);
}
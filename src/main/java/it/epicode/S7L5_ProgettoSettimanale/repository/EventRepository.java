package it.epicode.S7L5_ProgettoSettimanale.repository;

import it.epicode.S7L5_ProgettoSettimanale.model.Event;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizer(User organizer);
}
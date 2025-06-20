package it.epicode.S7L5_ProgettoSettimanale.service;

import it.epicode.S7L5_ProgettoSettimanale.dto.EventRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.EventResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.enumerating.Role;
import it.epicode.S7L5_ProgettoSettimanale.exception.BadRequestException;
import it.epicode.S7L5_ProgettoSettimanale.exception.ForbiddenException;
import it.epicode.S7L5_ProgettoSettimanale.exception.NotFoundException;
import it.epicode.S7L5_ProgettoSettimanale.model.Event;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDto create(EventRequestDto request, User organizer) {
        if (organizer.getRole() != Role.ORGANIZER) {
            throw new BadRequestException("Solo gli organizzatori possono creare eventi");
        }

        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate())
                .location(request.getLocation())
                .availableSeats(request.getAvailableSeats())
                .organizer(organizer)
                .build();

        event = eventRepository.save(event);
        return mapToResponse(event);
    }

    public List<EventResponseDto> findAll() {
        return eventRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EventResponseDto update(Long eventId, EventRequestDto request, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato"));


        if (!event.getOrganizer().getId().equals(user.getId())) {
            throw new ForbiddenException("Non puoi modificare o eliminare eventi non tuoi");
        }


        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setLocation(request.getLocation());
        event.setAvailableSeats(request.getAvailableSeats());

        return mapToResponse(eventRepository.save(event));
    }

    public void delete(Long eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Evento non trovato"));

        if (!event.getOrganizer().getId().equals(user.getId())) {
            throw new BadRequestException("Non puoi eliminare eventi non tuoi");
        }

        eventRepository.delete(event);
    }

    private EventResponseDto mapToResponse(Event e) {
        return EventResponseDto.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .date(e.getDate())
                .location(e.getLocation())
                .availableSeats(e.getAvailableSeats())
                .organizerEmail(e.getOrganizer().getEmail())
                .build();
    }
}

package it.epicode.S7L5_ProgettoSettimanale.controller;

import it.epicode.S7L5_ProgettoSettimanale.dto.EventRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.EventResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> create(
            @Valid @RequestBody EventRequestDto request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(201).body(eventService.create(request, user));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDto request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(eventService.update(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {
        eventService.delete(id, user);
        return ResponseEntity.ok("Evento eliminato");
    }
}

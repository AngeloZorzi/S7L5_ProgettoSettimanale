package it.epicode.S7L5_ProgettoSettimanale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private int availableSeats;
    private String organizerEmail;
}

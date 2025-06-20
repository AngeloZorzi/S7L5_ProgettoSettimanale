package it.epicode.S7L5_ProgettoSettimanale.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Future
    private LocalDateTime date;

    @NotBlank
    private String location;

    @Min(1)
    private int availableSeats;
}
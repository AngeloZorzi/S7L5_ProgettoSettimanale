package it.epicode.S7L5_ProgettoSettimanale.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    private Long id;
    private Long eventId;
    private String eventTitle;
    private String userEmail;
}

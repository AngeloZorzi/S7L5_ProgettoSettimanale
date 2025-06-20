package it.epicode.S7L5_ProgettoSettimanale.dto;

import it.epicode.S7L5_ProgettoSettimanale.model.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String role;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole().name();
    }
}

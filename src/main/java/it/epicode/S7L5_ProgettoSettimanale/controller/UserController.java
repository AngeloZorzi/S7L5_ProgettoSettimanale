package it.epicode.S7L5_ProgettoSettimanale.controller;

import it.epicode.S7L5_ProgettoSettimanale.dto.UserDto;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(new UserDto(user));
    }
}

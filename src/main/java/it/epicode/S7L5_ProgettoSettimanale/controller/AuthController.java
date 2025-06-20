package it.epicode.S7L5_ProgettoSettimanale.controller;

import it.epicode.S7L5_ProgettoSettimanale.dto.AuthResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.UserLoginRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.UserRegisterRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequestDto request) {
        authService.register(request);
        return ResponseEntity.status(201).body("Registrazione completata");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
package it.epicode.S7L5_ProgettoSettimanale.service;

import it.epicode.S7L5_ProgettoSettimanale.dto.AuthResponseDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.UserLoginRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.dto.UserRegisterRequestDto;
import it.epicode.S7L5_ProgettoSettimanale.enumerating.Role;
import it.epicode.S7L5_ProgettoSettimanale.exception.BadRequestException;
import it.epicode.S7L5_ProgettoSettimanale.model.User;
import it.epicode.S7L5_ProgettoSettimanale.repository.UserRepository;
import it.epicode.S7L5_ProgettoSettimanale.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void register(UserRegisterRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email già registrata");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public AuthResponseDto login(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Credenziali non valide"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Credenziali non valide");
        }

        return new AuthResponseDto(jwtUtil.generateToken(user));
    }
}

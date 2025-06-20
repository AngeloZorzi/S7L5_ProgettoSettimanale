package it.epicode.S7L5_ProgettoSettimanale.repository;

import it.epicode.S7L5_ProgettoSettimanale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

package it.epicode.S7L5_ProgettoSettimanale.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

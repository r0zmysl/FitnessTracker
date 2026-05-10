package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * DTO zawierające pełne dane użytkownika.
 *
 * @param id        identyfikator użytkownika
 * @param firstName imię
 * @param lastName  nazwisko
 * @param birthdate data urodzenia
 * @param email     adres e-mail
 */
public record UserDto(
        @Nullable Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
}

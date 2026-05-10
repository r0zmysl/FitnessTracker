package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO użytkownika zawierające tylko ID oraz adres e-mail.
 * Używane przy wyszukiwaniu po e-mailu.
 *
 * @param id    identyfikator użytkownika
 * @param email adres e-mail
 */
public record UserEmailDto(
        @Nullable Long id,
        String email) {
}

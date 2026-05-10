package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Uproszczone DTO użytkownika zawierające tylko ID oraz imię i nazwisko.
 *
 * @param id        identyfikator użytkownika
 * @param firstName imię
 * @param lastName  nazwisko
 */
public record UserSimpleDto(
        @Nullable Long id,
        String firstName,
        String lastName) {
}

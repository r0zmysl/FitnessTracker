package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs dostarczający operacje odczytu dla encji {@link User}.
 */
public interface UserProvider {

    /**
     * Pobiera użytkownika na podstawie jego ID.
     *
     * @param userId identyfikator użytkownika
     * @return {@link Optional} z użytkownikiem lub {@link Optional#empty()} jeśli nie znaleziono
     */
    Optional<User> getUser(Long userId);

    /**
     * Pobiera użytkownika na podstawie dokładnego adresu e-mail.
     *
     * @param email adres e-mail
     * @return {@link Optional} z użytkownikiem lub {@link Optional#empty()} jeśli nie znaleziono
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Wyszukuje użytkowników po fragmencie adresu e-mail (bez rozróżniania wielkości liter).
     *
     * @param emailFragment fragment adresu e-mail
     * @return lista użytkowników pasujących do podanego fragmentu
     */
    List<User> findUsersByEmailContaining(String emailFragment);

    /**
     * Pobiera wszystkich użytkowników.
     *
     * @return lista wszystkich użytkowników
     */
    List<User> findAllUsers();

    /**
     * Pobiera użytkowników urodzonych przed podaną datą (starszych niż data wskazuje).
     *
     * @param date data graniczna
     * @return lista użytkowników starszych niż podana data
     */
    List<User> findUsersOlderThan(LocalDate date);
}

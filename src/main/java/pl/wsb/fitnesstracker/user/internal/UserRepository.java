package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repozytorium JPA dla encji {@link User}.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wyszukuje użytkownika po dokładnym adresie e-mail (bez rozróżniania wielkości liter).
     *
     * @param email adres e-mail
     * @return {@link Optional} z użytkownikiem
     */
    Optional<User> findByEmailIgnoreCase(String email);

    /**
     * Wyszukuje użytkowników po fragmencie adresu e-mail (bez rozróżniania wielkości liter).
     *
     * @param emailFragment fragment e-maila
     * @return lista pasujących użytkowników
     */
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    /**
     * Wyszukuje użytkowników urodzonych przed podaną datą.
     *
     * @param date data graniczna
     * @return lista użytkowników urodzonych przed tą datą
     */
    List<User> findByBirthdateBefore(LocalDate date);
}

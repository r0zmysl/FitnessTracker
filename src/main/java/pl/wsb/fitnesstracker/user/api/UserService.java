package pl.wsb.fitnesstracker.user.api;

/**
 * Interfejs definiujący operacje zapisu i modyfikacji dla encji {@link User}.
 */
public interface UserService {

    /**
     * Tworzy nowego użytkownika.
     *
     * @param user użytkownik do utworzenia
     * @return utworzony użytkownik z przypisanym ID
     */
    User createUser(User user);

    /**
     * Usuwa użytkownika o podanym ID.
     *
     * @param userId identyfikator użytkownika do usunięcia
     */
    void deleteUser(Long userId);

    /**
     * Aktualizuje dane użytkownika o podanym ID.
     *
     * @param userId  identyfikator użytkownika
     * @param userDto nowe dane użytkownika
     * @return zaktualizowany użytkownik
     */
    User updateUser(Long userId, UserDto userDto);
}

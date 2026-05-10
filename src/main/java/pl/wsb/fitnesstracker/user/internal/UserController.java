package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Kontroler REST obsługujący operacje CRUD na użytkownikach systemu FitnessTracker.
 * Dostępny pod bazową ścieżką {@code /v1/users}.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Zwraca pełną listę wszystkich użytkowników wraz ze szczegółami.
     *
     * @return lista {@link UserDto}
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Zwraca uproszczoną listę użytkowników (tylko ID, imię i nazwisko).
     *
     * @return lista {@link UserSimpleDto}
     */
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     * Zwraca szczegóły użytkownika o podanym ID.
     *
     * @param id identyfikator użytkownika
     * @return {@link UserDto} z danymi użytkownika
     * @throws UserNotFoundException jeśli użytkownik nie istnieje
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Wyszukuje użytkowników po fragmencie adresu e-mail (bez rozróżniania wielkości liter).
     * Zwraca tylko ID i e-mail.
     *
     * @param email fragment adresu e-mail
     * @return lista {@link UserEmailDto}
     */
    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmailContaining(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Zwraca użytkowników urodzonych przed podaną datą (starszych niż data wskazuje).
     *
     * @param time data graniczna w formacie ISO (yyyy-MM-dd)
     * @return lista {@link UserDto}
     */
    @GetMapping("/older/{time}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Tworzy nowego użytkownika w systemie.
     *
     * @param userDto dane nowego użytkownika
     * @return utworzony użytkownik jako {@link UserDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User created = userService.createUser(user);
        return userMapper.toUserDto(created);
    }

    /**
     * Usuwa użytkownika o podanym ID.
     *
     * @param userId identyfikator użytkownika do usunięcia
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Aktualizuje dane użytkownika o podanym ID.
     *
     * @param userId  identyfikator użytkownika
     * @param userDto nowe dane użytkownika
     * @return zaktualizowany użytkownik jako {@link UserDto}
     */
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(userId, userDto);
        return userMapper.toUserDto(updated);
    }
}

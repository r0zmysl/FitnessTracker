package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja serwisu użytkowników.
 * Realizuje operacje odczytu ({@link UserProvider}) oraz zapisu i modyfikacji ({@link UserService}).
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting User with ID={}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, UserDto userDto) {
        log.info("Updating User with ID={}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.update(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findUsersByEmailContaining(String emailFragment) {
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersOlderThan(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }
}

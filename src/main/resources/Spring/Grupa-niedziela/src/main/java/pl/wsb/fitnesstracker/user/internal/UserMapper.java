package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Mapper odpowiedzialny za konwersję między encją {@link User} a obiektami DTO.
 */
@Component
class UserMapper {

    /**
     * Konwertuje encję {@link User} na {@link UserDto} (pełne dane).
     *
     * @param user encja użytkownika
     * @return pełne DTO użytkownika
     */
    UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    /**
     * Konwertuje encję {@link User} na {@link UserSimpleDto} (tylko ID, imię i nazwisko).
     *
     * @param user encja użytkownika
     * @return uproszczone DTO użytkownika
     */
    UserSimpleDto toUserSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    /**
     * Konwertuje encję {@link User} na {@link UserEmailDto} (tylko ID i e-mail).
     *
     * @param user encja użytkownika
     * @return DTO użytkownika z e-mailem
     */
    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(
                user.getId(),
                user.getEmail()
        );
    }

    /**
     * Konwertuje {@link UserDto} na encję {@link User} (bez ID).
     *
     * @param userDto DTO użytkownika
     * @return nowa encja użytkownika
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
    }
}

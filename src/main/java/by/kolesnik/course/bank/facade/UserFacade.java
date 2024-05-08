package by.kolesnik.course.bank.facade;

import by.kolesnik.course.bank.dto.CreateUserDto;
import by.kolesnik.course.bank.dto.UpdateUserDto;
import by.kolesnik.course.bank.dto.UserDto;
import by.kolesnik.course.bank.mapper.UserMapper;
import by.kolesnik.course.bank.service.UserService;
import by.kolesnik.course.bank.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto findById(Long id) {
        final User user = userService.findUserById(id);

        return userMapper.toDto(user);
    }

    public UserDto createUser(CreateUserDto dto) {
        final User user = userMapper.toEntity(dto);

        userService.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UpdateUserDto dto) {
        final User user = userService.findUserById(id);

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());

        userService.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUserPartially(Long id, UpdateUserDto dto) {
        final User user = userService.findUserById(id);

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        userService.save(user);

        return userMapper.toDto(user);
    }

    public void removeById(Long id) {
        userService.removeById(id);
    }
}

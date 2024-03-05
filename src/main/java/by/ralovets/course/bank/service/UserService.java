package by.ralovets.course.bank.service;

import by.ralovets.course.bank.dto.CreateUserDto;
import by.ralovets.course.bank.dto.UpdateUserDto;
import by.ralovets.course.bank.dto.UserDto;
import by.ralovets.course.bank.entity.User;
import by.ralovets.course.bank.exception.EntityNotFoundException;
import by.ralovets.course.bank.mapper.UserMapper;
import by.ralovets.course.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findDtoById(Long id) {
        return userMapper.toDto(
                findUserById(id)
        );
    }

    public User findUserById(Long id) {
        final Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        }

        return optionalUser.get();
    }

    // найти всех
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    // обновим всю информацию о пользователе
    // обновим часть информации о пользователе
    public UserDto createUser(CreateUserDto dto) {
        final User user = userMapper.toEntity(dto);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    // обновим всю информацию о пользователе
    public UserDto updateUser(Long id, UpdateUserDto dto) {
        final Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        final User user = optionalUser.get();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    // обновим часть информации о пользователе
    public UserDto updateUserPartially(Long id, UpdateUserDto dto) {
        final Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        final User user = optionalUser.get();

        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    // удалим пользователя
    public void removeById(Long id) {
        userRepository.removeById(id);
    }
}

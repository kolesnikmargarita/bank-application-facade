package by.kolesnik.course.bank.service;

import by.kolesnik.course.bank.exception.EntityNotFoundException;
import by.kolesnik.course.bank.entity.User;
import by.kolesnik.course.bank.repository.DeprecatedUserRepository;
import by.kolesnik.course.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        final Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("Пользователь с id '" + id + "' не найден");
        }

        return optionalUser.get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void removeById(Long id) {
        userRepository.deleteById(id);
    }
}

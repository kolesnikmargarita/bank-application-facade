package by.kolesnik.course.bank.repository;

import by.kolesnik.course.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull Optional<User> findById(@NonNull Long id);
    void removeById(@NonNull Long id);
}

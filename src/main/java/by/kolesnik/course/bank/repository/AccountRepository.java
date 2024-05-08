package by.kolesnik.course.bank.repository;

import by.kolesnik.course.bank.entity.Account;
import by.kolesnik.course.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    void deleteByUser(User user);
}

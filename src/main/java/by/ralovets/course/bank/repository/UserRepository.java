package by.ralovets.course.bank.repository;

import by.ralovets.course.bank.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // найти по id
    public Optional<User> findById(Long id) {
        return sessionFactory.fromTransaction(session -> {
            final Query<User> query = session.createQuery("FROM User U WHERE U.id = :id", User.class);

            query.setParameter("id", id);

            return query.uniqueResultOptional();
        });
    }

    // найти всех
    public List<User> findAll() {
        return sessionFactory.fromTransaction(session -> {
            final Query<User> query = session.createQuery("FROM User", User.class);

            return query.list();
        });
    }

    // обновим всю информацию о пользователе
    // обновим часть информации о пользователе
    public User save(User user) {
        return sessionFactory.fromTransaction(session -> {
            session.persist(user);

            return user;
        });
    }

    // удалим пользователя
    public void removeById(Long id) {
        sessionFactory.inTransaction(session -> {
            final MutationQuery query = session.createMutationQuery("DELETE FROM User U WHERE id = :id");

            query.setParameter("id", id);

            query.executeUpdate();
        });
    }
}

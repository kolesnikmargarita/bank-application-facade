package by.kolesnik.course.bank.repository;

import by.kolesnik.course.bank.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeprecatedUserRepository {

    private final SessionFactory sessionFactory = null;

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
            // fixed: detached entity passed to persist: by.ralovets.course.bank.entity.User
            if (user.getId() == null) {
                session.persist(user);
            } else {
                session.merge(user);
            }

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

package nl.nusayba.oose.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.nusayba.oose.domain.entities.Users;

public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Users findByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Users findByToken(String token) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Users u WHERE u.token = :token", Users.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public Users findByUsername(String username) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM Users u WHERE u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void update(Users users) {
        entityManager.merge(users);
    }
}

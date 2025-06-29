//package nl.nusayba.oose.domain.repositories;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import nl.nusayba.oose.domain.entities.Users;
//
//public class LoginRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public Users findByUsernameAndPassword(String username, String password) {
//        try {
//            return entityManager.createQuery(
//                            "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password", Users.class)
//                    .setParameter("username", username)
//                    .setParameter("password", password)
//                    .getSingleResult();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    @Transactional
//    public void save(Users users) {
//        entityManager.persist(users);
//    }
//}

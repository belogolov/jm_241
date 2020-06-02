package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("jpaUser")
public class JpaUserDao implements UserDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void add(User user) {
      em.persist(user);
   }

   @Override
   public void delete(User user) {
      em.remove(user);
   }

   @Override
   public void delete(Long id) {
      delete(getUserById(id));
   }

   @Override
   public void update(User user) {
//      em.remove(user);
   }

   @Override
   public List<User> listUsers() {
      return em.createQuery("FROM User", User.class).getResultList();
   }

   @Override
   public User getUserById(Long id) {
      return em.find(User.class, id);
   }

   @Override
   public User getUserByEmail(String email) {
      User user = null;
      try {
         user = em.createQuery("FROM User u WHERE u.email=:email", User.class)
                 .setParameter("email", email)
                 .setMaxResults(1)
                 .getSingleResult();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return user;
   }

}

package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void delete(User user) {
      sessionFactory.getCurrentSession().delete(user);
   }

   @Override
   public void delete(Long id) {
      delete(getUserById(id));
   }

   @Override
   public void update(User user) {
      sessionFactory.getCurrentSession().update(user);
   }

   @Override
   public User validUser(String login, String password) {
      User user = getUserByEmail(login);
      if (user != null && user.getPassword().equals(password)) {
         return user;
      }
      return null;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public User getUserById(Long id) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.id=:id");
      query.setParameter("id", id);
      query.setMaxResults(1);
      return query.getSingleResult();
   }

   @Override
   public User getUserByEmail(String email) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.email=:email");
      query.setParameter("email", email);
      query.setMaxResults(1);
      User u = query.getSingleResult();
      return u;
   }

}

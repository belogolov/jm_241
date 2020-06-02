package hiber.service;

import hiber.dao.RoleDao;
import hiber.dao.UserDao;
import hiber.model.Role;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private RoleDao roleDao;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Transactional
   @Override
   public void add(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      Set<Role> roles = new HashSet<>();
      roles.add(roleDao.getRoleById(2L));
      user.setRoles(roles);
      userDao.add(user);
   }

   @Transactional
   @Override
   public void delete(User user) {
      userDao.delete(user);
   }

   @Transactional
   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

   @Transactional
   @Override
   public void update(User user, String roles) {
      //todo при отключенном фильтре CharsetFilter - user приходит в неправильной кодировке
      Set<Role> collect = Arrays.stream(roles.split(","))
              .map(s->s.trim())
              .filter(s -> s.length() > 0)
              .map(roleDao::getRoleByName)
              .filter(role -> role != null)
              .collect(Collectors.toSet());

      User entity = getUserById(user.getId());
      if(entity!=null){
         entity.setFirstName(user.getFirstName());
         entity.setLastName(user.getLastName());
         entity.setEmail(user.getEmail());
         if(!user.getPassword().equals(entity.getPassword())){
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
         }
         entity.setRoles(collect);
      }
   }

   @Override
   public List<User> listUsers() {
      if (userDao != null) {
         return userDao.listUsers();
      }
      return new ArrayList<>();
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   public User getUserByEmail(String email) {
      return userDao.getUserByEmail(email);
   }

}

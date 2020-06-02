package hiber.dao;

import hiber.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository("jpaRole")
public class JpaRoleDao implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRoleById(Long id) {
        Role role = em.find(Role.class, id);
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        try {
            return em.createQuery("SELECT u FROM Role u WHERE u.role=:role", Role.class)
                    .setParameter("role", name)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

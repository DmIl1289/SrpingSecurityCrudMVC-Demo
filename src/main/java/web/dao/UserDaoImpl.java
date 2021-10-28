package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager entityManager;


    @Override
    public List<User> listUsers() {
        TypedQuery<User> users = entityManager.createQuery("from User", User.class);
        return users.getResultList();
    }

    @Override
    public void add(User user) {
        user.setRoles(new HashSet<>(getListOfRoles(user)));

        entityManager.persist(user);
    }

    private List<Role> getListOfRoles(User user) {
        List<Role> roleList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            List<Role> found = entityManager.createQuery("from Role where roles=:name", Role.class)
                    .setParameter("name", role.getRole()).getResultList();
            roleList.addAll(found);
        }
        return roleList;
    }

    @Override
    public User show (long id) {
        return (User) entityManager.createQuery("from User where id= :ID")
                .setParameter("ID", id).getSingleResult();
    }

    @Override
    public void remove(long id) {
        entityManager.createQuery("delete User where id=:ID")
                .setParameter("ID", id).executeUpdate();
    }

    @Override
    public void update(long id, User user) {
        User user1 = entityManager.createQuery("from User where id=:ID", User.class)
                .setParameter("ID", id).getSingleResult();
        getListOfRoles(user);
        user1.setUsername(user.getUsername());
        user1.setSurName(user.getSurName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRoles(new HashSet<>(getListOfRoles(user)));
        entityManager.merge(user1);
    }

    @Override
    public User findByName(String username) {
        return (User)entityManager.createQuery("from User where name=:NAME")
                .setParameter("NAME", username).getSingleResult();
    }
}

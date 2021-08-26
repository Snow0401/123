package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
        TypedQuery<User> query =
                entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    @Override
    public void update(long id, User user) {
        User updatedUser = get(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAge(user.getAge());
        updatedUser.setName(user.getName());
        updatedUser.setRoles(user.getRoles());
    }

    @Transactional
    @Override
    public void updatePassword(long id, String newPassword) {
        User updatedUser = get(id);
        updatedUser.setPassword(newPassword);
//        entityManager.flush();
    }

    @Transactional
    @Override
    public void delete(long id) {
        entityManager.remove(get(id));
    }
}

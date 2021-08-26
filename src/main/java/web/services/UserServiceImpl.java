package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        if (getByName(user.getName()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.add(user);
        }
    }

    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void updatePassword(long id, String newPassword) {
        userDao.updatePassword(id, passwordEncoder.encode(newPassword));
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }
}

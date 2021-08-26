package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User get(long id);

    List<User> getAll();

    void update(long id, User user);

    void updatePassword(long id, String newPassword);

    void delete(long id);

    User getByName(String name);

}

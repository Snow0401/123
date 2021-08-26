package web.services;

import web.models.Role;

import java.util.List;

public interface RoleService {
    void add(Role role);

    Role get(int id);

    Role getByRoleName(String roleName);

    List<Role> getAll();

    void update(int id, Role role);

    void delete(int id);
}

package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(Role role) {
        List<Role> roles = getAll();
        if (!roles.contains(role)) {
            if (role.getRoleName().length() < 5 || !role.getRoleName().substring(0,5).equals("ROLE_")) {
                role.setRoleName("ROLE_" + role.getRoleName());
            }
            roleDao.add(role);
        }
    }

    @Override
    public Role get(int id) {
        return roleDao.get(id);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleDao.getByRoleName(roleName);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public void update(int id, Role role) {
        if (role.getRoleName().length() < 5 || !role.getRoleName().substring(0,5).equals("ROLE_")) {
            role.setRoleName("ROLE_" + role.getRoleName());
        }
        roleDao.update(id, role);
    }

    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }
}

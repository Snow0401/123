package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UsersInit {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void loadTestUsers() {

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setDescription("Allow user's and roles administration");
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setDescription("Simply user");
        roleService.add(userRole);

        Set<Role> roles = new HashSet<>();
        User userAdmin = new User("Marina", "Tkachova", "marina@gmail.com", (byte) 24, "admin", "admin");
        roles.add(userRole);
        roles.add(adminRole);
        userAdmin.setRoles(roles);
        userService.add(userAdmin);

        roles.clear();
        User user1 = new User("Igor", "Pypkin", "nike@gmail.com", (byte) 31, "user", "user");
        roles.add(userRole);
        user1.setRoles(roles);
        userService.add(user1);
    }
}

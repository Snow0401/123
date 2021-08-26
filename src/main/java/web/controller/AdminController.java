package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private void prepareUser(User user) {
        for (Role role : user.getRoles()) {
            role.setId(roleService.getByRoleName(role.getRoleName()).getId());
        }
    }

    @GetMapping("users")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/index";
    }

    @GetMapping("users/{id}")
    public String printUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "users/info";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user) {
        prepareUser(user);
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleService.getAll());
        return "users/new";
    }

    @GetMapping("users/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roleList", roleService.getAll());
        return "/users/edit";
    }

    @GetMapping("users/{id}/change_password")
    public String changePassword(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "/users/change_password";
    }

    @PatchMapping("users/{id}/change_password")
    public String updatePassword(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updatePassword(id, user.getPassword());
        return "redirect:/admin/users";
    }

    @PatchMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        prepareUser(user);
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("roles")
    public String printRoles(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "roles/index";
    }

    @GetMapping("roles/{id}")
    public String printRoles(@PathVariable("id") int id, Model model) {
        model.addAttribute("role", roleService.get(id));
        return "roles/info";
    }

    @PostMapping("roles")
    public String createRole(@ModelAttribute("role") Role role) {
        roleService.add(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("roles/new")
    public String newRole(@ModelAttribute("role") Role role) {
        return "roles/new";
    }

    @GetMapping("roles/{id}/edit")
    public String editRole(@PathVariable("id") int id, Model model) {
        model.addAttribute("role", roleService.get(id));
        return "roles/edit";
    }

    @PatchMapping("roles/{id}")
    public String updateRole(@ModelAttribute("role") Role role, @PathVariable("id") int id) {
        roleService.update(id, role);
        return "redirect:/admin/roles";
    }

    @DeleteMapping("roles/{id}")
    public String deleteRole(@PathVariable("id") int id) {
        roleService.delete(id);
        return "redirect:/admin/roles";
    }
}

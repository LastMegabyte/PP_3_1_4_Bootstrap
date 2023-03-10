package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/adminPage")
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPage";
    }

    @DeleteMapping("adminPage/{id}/delete")
    public String dropUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/adminPage";
    }

    @PutMapping("adminPage/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin/adminPage";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getRole(role.getName()))
                .collect(Collectors.toSet()));
    }

    @GetMapping("/add")
    public String newUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.addUser(user);
        return "redirect:/admin/adminPage";
    }
}

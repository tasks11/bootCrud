package com.controllers;

import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getUsers(ModelMap model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("user", users);
        return "allUsers";
    }

    @GetMapping("/add")
    public String addGet() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("user") User user, @RequestParam String role) {
        Role role1 = new Role(role);
        user.setRoles(Collections.singleton(role1));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@ModelAttribute("user") User users, ModelMap modelMap) {
        Optional<User> user = userRepository.findById(users.getId());
        modelMap.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edit")
    public String editPage(@ModelAttribute("user") User user, @RequestParam String role) {
        Role role1 = new Role(role);
        user.setRoles(Collections.singleton(role1));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("user") User users) {
        userRepository.deleteById(users.getId());
        return "redirect:/admin";
    }
 }

package com.controllers;

import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsers(ModelMap model) {
        Iterable<User> users = userRepository.findAll();
        User autUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", users);
        model.addAttribute("autUser", autUser);
        return "allUsers";
    }

    @GetMapping("/add")
    public String addGet(User user) {
//        modelMap.addAttribute("roles", roleRepository.findAll());
        return "addUser";
    }

//    @PostMapping("/add")
//    public String addPost(@ModelAttribute("user") User user, @RequestParam String role,
//                          @RequestParam String username) {
//        Role role1 = new Role(role);
//        user.setUserName(username);
//        user.setRoles(Collections.singleton(role1));
//        userRepository.save(user);
//        return "redirect:/admin";
//    }

//    @PostMapping("/add")
//    public String addPost(@ModelAttribute UserDto userDto) {
//        userRepository.save(userDto.dtoToUser(userDto));
//        return "redirect:/admin";
//    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam Long id, ModelMap modelMap) {
        Optional<User> user = userRepository.findById(id);
        modelMap.addAttribute("user", user);
        return "allUsers";
    }

//    @PutMapping("/edit")
//    public String postEdit(@ModelAttribute("user") User user, @RequestParam String role) {
//        Role role1 = new Role(role);
//        user.setRoles(Collections.singleton(role1));
//        userRepository.save(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam Long id) {
//        userRepository.deleteById(id);
//        return "redirect:/admin";
//    }
}

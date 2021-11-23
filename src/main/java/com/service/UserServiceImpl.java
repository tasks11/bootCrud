package com.service;

import com.dto.UserDto;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void addUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getLastname(), userDto.getAge(),
                userDto.getEmail(), userDto.getPassword());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        if (userDto.getRole().equals("ROLE_ADMIN")) {
            role.setRole("ROLE_ADMIN");
            roles.add(role);
        } else {
            role.setRole("ROLE_USER");
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(UserDto userDto) {
        User user = getUserById(userDto.getId());
        user.setUserName(userDto.getUsername());
        user.setLastname(userDto.getLastname());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        if (userDto.getRole().equals("ROLE_ADMIN")) {
            role1.setRole("ROLE_ADMIN");
            roles.add(role1);
        } else {
            role1.setRole("ROLE_USER");
            roles.add(role1);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }
}

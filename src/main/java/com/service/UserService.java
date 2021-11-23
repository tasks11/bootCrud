package com.service;

import com.dto.UserDto;
import com.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void addUser(UserDto userDto);
    void deleteUser(Long id);
    void editUser(UserDto userDto);
    User getUserById(Long id);
    List<User> getUser();
}

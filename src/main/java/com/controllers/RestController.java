package com.controllers;

import com.dto.UserDto;
import com.model.Role;
import com.model.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("clients")
public class RestController {

    @Autowired
    UserService userService;

    //ResponseEntity — специальный класс для возврата ответов. С помощью него мы сможем в дальнейшем вернуть клиенту HTTP статус код.
    //@RequestBody User user, значение этого параметра подставляется из тела запроса. Об этом говорит аннотация  @RequestBody

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        final List<User> users = userService.getUser();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") long id) {
        User user = userService.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> putEdit(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.editUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

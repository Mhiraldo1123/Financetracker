package com.marvin.financetracker.controller;

import com.marvin.financetracker.model.User;
import com.marvin.financetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")// adds /api/users to the base url
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){ //@RequestBody extracts what the user sends in the request
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){ //@PathVariable Get user by id from the uri path /api/users/{id}
        return userService.findById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}

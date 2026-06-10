package com.marvin.financetracker.controller;

import com.marvin.financetracker.model.User;
import com.marvin.financetracker.service.TransactionService;
import com.marvin.financetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")// adds /api/users to the base url
public class UserController {
    private final UserService userService;
    private final TransactionService transactionService;

    public UserController(UserService userService, TransactionService transactionService){
        this.userService = userService;
        this.transactionService = transactionService;
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

    @GetMapping("/summary/{id}")
    public Map<String, BigDecimal> getUserSummary(@PathVariable Long id){
        return transactionService.getSummary(id);
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

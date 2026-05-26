package com.marvin.financetracker.service;

import com.marvin.financetracker.model.User;
import com.marvin.financetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { //Constructor
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    //find by user id

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    //find by username
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    //get by email
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    //get all users
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //update a user
    public User updateUser(User user){ //same as create, looks to see if id exists to update if not create
        return userRepository.save(user);
    }

    //delete a user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}

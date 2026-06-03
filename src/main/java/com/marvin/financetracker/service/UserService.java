package com.marvin.financetracker.service;

import com.marvin.financetracker.model.User;
import com.marvin.financetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

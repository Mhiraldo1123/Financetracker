package com.marvin.financetracker.repository;

//import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marvin.financetracker.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); //Optional may not find anything
    Optional<User> findByEmail(String email);
}

package com.marvin.financetracker.repository;

import com.marvin.financetracker.model.Category;
import com.marvin.financetracker.model.Transaction;
import com.marvin.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategory(Category category); //Can search transactions by category
    List<Transaction> findByUser(User user);
}

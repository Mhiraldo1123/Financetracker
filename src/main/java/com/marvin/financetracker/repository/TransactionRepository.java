package com.marvin.financetracker.repository;

import com.marvin.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategory_Id(Long id); //Can search transactions by category
    List<Transaction> findByUser_Id(Long id);


}

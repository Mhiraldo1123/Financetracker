package com.marvin.financetracker.service;

import com.marvin.financetracker.exception.ResourceNotFoundException;
import com.marvin.financetracker.model.CategoryType;
import com.marvin.financetracker.model.Transaction;
import com.marvin.financetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    public List<Transaction> findByCategoryId(Long id){
        return transactionRepository.findByCategory_Id(id);
    }

    public List<Transaction> findByUserId(Long id){
        return transactionRepository.findByUser_Id(id);
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public Map<String, BigDecimal> getSummary(Long id){
        List<Transaction> transactions = transactionRepository.findByUser_Id(id);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getCategory().getType() == CategoryType.INCOME)
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = transactions.stream()
                .filter(t -> t.getCategory().getType() == CategoryType.EXPENSE)
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpenses);

        Map<String, BigDecimal> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpenses", totalExpenses);
        summary.put("balance", balance);

        return summary;
    }

}

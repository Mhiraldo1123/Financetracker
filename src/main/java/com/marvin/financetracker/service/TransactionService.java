package com.marvin.financetracker.service;

import com.marvin.financetracker.model.Transaction;
import com.marvin.financetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return transactionRepository.findById(id).orElse(null);
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

}

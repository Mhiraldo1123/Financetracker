package com.marvin.financetracker.controller;

import com.marvin.financetracker.model.Transaction;
import com.marvin.financetracker.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) //create transaction
    {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.findAll();
    }

    @GetMapping("/category/{id}")
    public List<Transaction> getTransactionsByCategory(@PathVariable Long id){
        return transactionService.findByCategoryId(id);
    }

    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long id){
        return transactionService.findByUserId(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
    }

}

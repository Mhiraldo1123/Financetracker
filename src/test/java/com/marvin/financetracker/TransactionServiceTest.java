package com.marvin.financetracker;

import com.marvin.financetracker.model.Category;
import com.marvin.financetracker.model.CategoryType;
import com.marvin.financetracker.model.Transaction;
import com.marvin.financetracker.model.User;
import com.marvin.financetracker.repository.TransactionRepository;

import com.marvin.financetracker.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testCreateTransaction(){
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("100.00"));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(transaction);
        assertEquals(transaction, result);
    }

    @Test
    public void testDeleteTransaction(){
        transactionService.deleteTransaction(1L);
        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetSummary(){
        User user = new User();
        Category categoryIncome = new Category();
        Category categoryExpense = new Category();
        categoryIncome.setType(CategoryType.INCOME);
        categoryExpense.setType(CategoryType.EXPENSE);

        Transaction transactionIncome = new Transaction();
        Transaction transactionExpense = new Transaction();
        List<Transaction> transactions = new ArrayList<>();

        transactionIncome.setAmount(new BigDecimal("100.00"));
        transactionIncome.setCategory(categoryIncome);

        transactionExpense.setAmount(new BigDecimal("50.00"));
        transactionExpense.setCategory(categoryExpense);

        transactions.add(transactionIncome);
        transactions.add(transactionExpense);

        when(transactionRepository.findByUser_Id(user.getId())).thenReturn(transactions);

        Map<String, BigDecimal> result = transactionService.getSummary(user.getId());

        assertEquals(new BigDecimal("100.00"), result.get("totalIncome"));
        assertEquals(new BigDecimal("50.00"), result.get("totalExpenses"));
        assertEquals(new BigDecimal("50.00"), result.get("balance"));
    }
}



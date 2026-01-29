package com.finance.system.controller;

import com.finance.system.model.Transaction;
import com.finance.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_FINANCE_ADMIN', 'ROLE_FINANCE_EDITOR', 'ROLE_FINANCE_VIEWER')")
    public List<Transaction> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_FINANCE_ADMIN', 'ROLE_FINANCE_EDITOR')")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_FINANCE_ADMIN', 'ROLE_FINANCE_EDITOR')")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_FINANCE_ADMIN')")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
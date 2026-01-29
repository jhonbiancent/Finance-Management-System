package com.finance.system.controller;

import com.finance.system.dto.TransactionDTO;
import com.finance.system.model.Transaction;
import com.finance.system.repository.TransactionRepository;
import com.finance.system.service.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDto)
                .collect(Collectors.toList());
    }
}
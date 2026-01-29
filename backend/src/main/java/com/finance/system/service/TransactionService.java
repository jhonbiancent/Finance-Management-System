package com.finance.system.service;

import com.finance.system.model.Transaction;
import com.finance.system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));

        transaction.setCheckNumber(transactionDetails.getCheckNumber());
        transaction.setCheckDate(transactionDetails.getCheckDate());
        transaction.setStatus(transactionDetails.getStatus());
        transaction.setBalance(transactionDetails.getBalance());
        transaction.setDueDate(transactionDetails.getDueDate());
        transaction.setTermsOfDays(transactionDetails.getTermsOfDays());
        transaction.setMode(transactionDetails.getMode());
        transaction.setType(transactionDetails.getType());
        transaction.setQuantity(transactionDetails.getQuantity());
        transaction.setFuelPurchase(transactionDetails.getFuelPurchase());
        transaction.setInvoiceBillingNumber(transactionDetails.getInvoiceBillingNumber());
        transaction.setFreightAmount(transactionDetails.getFreightAmount());
        transaction.setFreightBilling(transactionDetails.getFreightBilling());
        transaction.setCommissionName(transactionDetails.getCommissionName());
        transaction.setCommissionAmount(transactionDetails.getCommissionAmount());
        transaction.setCommissionOnBilling(transactionDetails.getCommissionOnBilling());
        transaction.setPayments(transactionDetails.getPayments());
        transaction.setRemarks(transactionDetails.getRemarks());
        transaction.setBacktrack(transactionDetails.getBacktrack());
        transaction.setStatus2(transactionDetails.getStatus2());
        transaction.setStatus3(transactionDetails.getStatus3());
        transaction.setTransactionGroup(transactionDetails.getTransactionGroup());
        transaction.setRevenueCategory(transactionDetails.getRevenueCategory());
        transaction.setBillingNumber(transactionDetails.getBillingNumber());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setBmmiCustomer(transactionDetails.getBmmiCustomer());
        transaction.setTin(transactionDetails.getTin());
        transaction.setVatNonvat(transactionDetails.getVatNonvat());
        transaction.setVat(transactionDetails.getVat());
        transaction.setAddress(transactionDetails.getAddress());
        transaction.setGrossSales(transactionDetails.getGrossSales());
        transaction.setDiscount(transactionDetails.getDiscount());
        transaction.setNetSales(transactionDetails.getNetSales());
        transaction.setSalesVatExclusive(transactionDetails.getSalesVatExclusive());
        transaction.setEwt(transactionDetails.getEwt());
        transaction.setServiceCharge(transactionDetails.getServiceCharge());
        transaction.setStrippingFee(transactionDetails.getStrippingFee());
        transaction.setTsReturn(transactionDetails.getTsReturn());
        transaction.setInterestIncome(transactionDetails.getInterestIncome());
        transaction.setBalance2(transactionDetails.getBalance2());
        transaction.setParticulars(transactionDetails.getParticulars());
        transaction.setBank(transactionDetails.getBank());
        transaction.setFromBankSub(transactionDetails.getFromBankSub());
        transaction.setToBankSub(transactionDetails.getToBankSub());
        transaction.setAmount(transactionDetails.getAmount());

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}


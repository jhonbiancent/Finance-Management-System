package com.finance.system.config;

import com.finance.system.model.Transaction;
import com.finance.system.model.User;
import com.finance.system.model.Role;
import com.finance.system.repository.TransactionRepository;
import com.finance.system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadTransactionData(TransactionRepository transactionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create a default user
            if (userRepository.findByUsername("user@example.com").isEmpty()) {
                User defaultUser = User.builder()
                        .firstname("Default")
                        .lastname("User")
                        .username("user@example.com")
                        .password(passwordEncoder.encode("password"))
                        .role(Role.COLLECTION)
                        .build();
                userRepository.save(defaultUser);
                System.out.println("Default user 'user@example.com' created.");
            } else {
                System.out.println("Default user 'user@example.com' already exists.");
            }

            System.out.println("Loading 10 fake transactions...");
            for (int i = 0; i < 10; i++) {
                Transaction transaction = Transaction.builder()
                        .checkNumber("CHK-" + UUID.randomUUID().toString().substring(0, 8))
                        .checkDate(new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24 * i))) // Dates in the past
                        .status(i % 2 == 0 ? "Pending" : "Completed")
                        .balance(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100, 10000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .dueDate(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * (10 - i)))) // Dates in the future
                        .termsOfDays(ThreadLocalRandom.current().nextInt(15, 60))
                        .mode(i % 3 == 0 ? "Cash" : (i % 3 == 1 ? "Credit" : "Online"))
                        .type(i % 2 == 0 ? "Income" : "Expense")
                        .quantity(ThreadLocalRandom.current().nextInt(1, 100))
                        .fuelPurchase(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(50, 500)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .invoiceBillingNumber("INV-" + UUID.randomUUID().toString().substring(0, 8))
                        .freightAmount(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10, 200)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .freightBilling("FRB-" + UUID.randomUUID().toString().substring(0, 8))
                        .commissionName("Commission Agent " + i)
                        .commissionAmount(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(5, 50)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .commissionOnBilling(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.01, 0.1)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .payments(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100, 5000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .remarks("Fake transaction remark " + i)
                        .backtrack(i % 2 == 0 ? "Yes" : "No")
                        .status2("Status2-" + i)
                        .status3("Status3-" + i)
                        .group("Group-" + (i % 3 + 1))
                        .revenueCategory("Category-" + (i % 4 + 1))
                        .billingNumber("BLG-" + UUID.randomUUID().toString().substring(0, 8))
                        .description("Description for transaction " + i)
                        .bmmiCustomer("Customer " + i)
                        .tin("TIN-" + ThreadLocalRandom.current().nextLong(100000000, 999999999L))
                        .vatNonvat(i % 2 == 0 ? "VAT" : "Non-VAT")
                        .vat(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 0.12)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .address("Address " + i + ", Fake Street")
                        .grossSales(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1000, 20000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .discount(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 500)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .netSales(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(900, 19000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .salesVatExclusive(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(800, 18000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .ewt(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 0.05)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .serviceCharge(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 100)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .strippingFee(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 50)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .returnValue(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 100)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .interestIncome(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 20)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        .particulars("Particulars for " + i)
                        .bank("Bank " + (i % 3 + 1))
                        .fromBankSub("FromBankSub-" + i)
                        .toBankSub("ToBankSub-" + i)
                        .amount(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(500, 15000)).setScale(2, BigDecimal.ROUND_HALF_UP))
                        // Relationships are left null for simplicity
                        .build();
                transactionRepository.save(transaction);
            }
            System.out.println("10 fake transactions loaded.");
        };
    }
}

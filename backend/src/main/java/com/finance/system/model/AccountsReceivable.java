package com.finance.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts_receivable")
public class AccountsReceivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ar_id;

    private BigDecimal ar_total_accounts_receivable;
    private BigDecimal ar_subtotal;
    private BigDecimal ar_collection;
    private BigDecimal ar_performance;
    private BigDecimal ar_excess_payment_from_client;
    private BigDecimal ar_write_off;
}

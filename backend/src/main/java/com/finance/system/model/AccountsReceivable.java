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
    @Column(name = "AR_ID")
    private Long id;

    private String name;

    @Column(name = "AR_total_accounts_receivable")
    private BigDecimal totalAccountsReceivable;

    @Column(name = "AR_subtotal")
    private BigDecimal subtotal;

    @Column(name = "AR_collection")
    private BigDecimal collection;

    @Column(name = "AR_performance")
    private String performance;

    @Column(name = "AR_excess_payment_from_client")
    private BigDecimal excessPaymentFromClient;

    @Column(name = "AR_write_off")
    private BigDecimal writeOff;
}

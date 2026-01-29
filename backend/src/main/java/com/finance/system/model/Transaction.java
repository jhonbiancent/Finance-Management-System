package com.finance.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SB_subsidiary_id")
    private Subsidiary subsidiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DS_disbursement_id")
    private Disbursement disbursement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LG_logistic_id")
    private Logistics logistics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PR_products_id")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AR_id")
    private AccountsReceivable accountsReceivable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CR_id")
    private Controller controller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AP_id")
    private AccountsPayable accountsPayable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "TS_check_number")
    private String checkNumber;

    @Column(name = "TS_check_date")
    private Date checkDate;

    @Column(name = "TS_status")
    private String status;

    @Column(name = "TS_balance")
    private BigDecimal balance;

    @Column(name = "TS_due_date")
    private Date dueDate;

    @Column(name = "TS_terms_of_days")
    private Integer termsOfDays;

    @Column(name = "TS_mode")
    private String mode;

    @Column(name = "TS_type")
    private String type;

    @Column(name = "TS_quantity")
    private Integer quantity;

    @Column(name = "TS_fuel_purchase")
    private BigDecimal fuelPurchase;

    @Column(name = "TS_invoice_billing_number")
    private String invoiceBillingNumber;

    @Column(name = "TS_freight_amount")
    private BigDecimal freightAmount;

    @Column(name = "TS_freight_billing")
    private String freightBilling;

    @Column(name = "TS_commission_name")
    private String commissionName;

    @Column(name = "TS_commission_amount")
    private BigDecimal commissionAmount;

    @Column(name = "TS_commission_on_billing")
    private BigDecimal commissionOnBilling;

    @Column(name = "TS_payments")
    private BigDecimal payments;

    @Column(name = "TS_remarks")
    private String remarks;

    @Column(name = "TS_backtrack")
    private String backtrack;

    @Column(name = "TS_status2")
    private String status2;

    @Column(name = "TS_status3")
    private String status3;

    @Column(name = "TS_group")
    private String group;

    @Column(name = "TS_revenue_category")
    private String revenueCategory;

    @Column(name = "TS_billing_number")
    private String billingNumber;

    @Column(name = "TS_description")
    private String description;

    @Column(name = "TS_bmmi_customer")
    private String bmmiCustomer;

    @Column(name = "TS_tin")
    private String tin;

    @Column(name = "TS_vat_nonvat")
    private String vatNonvat;

    @Column(name = "TS_vat")
    private BigDecimal vat;

    @Column(name = "TS_address")
    private String address;

    @Column(name = "TS_gross_sales")
    private BigDecimal grossSales;

    @Column(name = "TS_discount")
    private BigDecimal discount;

    @Column(name = "TS_net_sales")
    private BigDecimal netSales;

    @Column(name = "TS_sales_vat_exclusive")
    private BigDecimal salesVatExclusive;

    @Column(name = "TS_ewt")
    private BigDecimal ewt;

    @Column(name = "TS_service_charge")
    private BigDecimal serviceCharge;

    @Column(name = "TS_stripping_fee")
    private BigDecimal strippingFee;

    @Column(name = "TS_return")
    private BigDecimal returnValue;

    @Column(name = "TS_interest_income")
    private BigDecimal interestIncome;

    // Duplicated TS_balance in ERD, using the one defined above
    // private BigDecimal balance;

    @Column(name = "TS_particulars")
    private String particulars;

    @Column(name = "TS_bank")
    private String bank;

    @Column(name = "TS_from_bank_sub")
    private String fromBankSub;

    @Column(name = "TS_to_bank_sub")
    private String toBankSub;

    @Column(name = "TS_amount")
    private BigDecimal amount;
}
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
    @Column(name = "ts_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "sb_subsidiary_id")
    private Subsidiary subsidiary;

    @ManyToOne
    @JoinColumn(name = "ds_disbursement_id")
    private Disbursement disbursement;

    @ManyToOne
    @JoinColumn(name = "lg_logistic_id")
    private Logistics logistics;

    @ManyToOne
    @JoinColumn(name = "pr_products_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "ar_id")
    private AccountsReceivable accountsReceivable;

    @ManyToOne
    @JoinColumn(name = "cr_id")
    private Controller controller;

    @Column(name = "ts_check_number")
    private String checkNumber;
    @Column(name = "ts_check_date")
    private Date checkDate;
    @Column(name = "ts_status")
    private String status;
    @Column(name = "ts_balance")
    private BigDecimal balance;
    @Column(name = "ts_due_date")
    private Date dueDate;
    @Column(name = "ts_terms_of_days")
    private Integer termsOfDays;
    @Column(name = "ts_mode")
    private String mode;
    @Column(name = "ts_type")
    private String type;
    @Column(name = "ts_quantity")
    private Integer quantity;
    @Column(name = "ts_fuel_purchase")
    private BigDecimal fuelPurchase;
    @Column(name = "ts_invoice_billing_number")
    private String invoiceBillingNumber;
    @Column(name = "ts_freight_amount")
    private BigDecimal freightAmount;
    @Column(name = "ts_freight_billing")
    private BigDecimal freightBilling;
    @Column(name = "ts_commission_name")
    private String commissionName;
    @Column(name = "ts_commission_amount")
    private BigDecimal commissionAmount;
    @Column(name = "ts_commission_on_billing")
    private BigDecimal commissionOnBilling;
    @Column(name = "ts_payments")
    private BigDecimal payments;
    @Column(name = "ts_remarks")
    private String remarks;
    @Column(name = "ts_backtrack")
    private String backtrack;
    @Column(name = "ts_status2")
    private String status2;
    @Column(name = "ts_status3")
    private String status3;
    @Column(name = "ts_group")
    private String transactionGroup;
    @Column(name = "ts_revenue_category")
    private String revenueCategory;
    @Column(name = "ts_billing_number")
    private String billingNumber;
    @Column(name = "ts_description")
    private String description;
    @Column(name = "ts_bmmi_customer")
    private String bmmiCustomer;
    @Column(name = "ts_tin")
    private String tin;
    @Column(name = "ts_vat_nonvat")
    private BigDecimal vatNonvat;
    @Column(name = "ts_vat")
    private BigDecimal vat;
    @Column(name = "ts_address")
    private String address;
    @Column(name = "ts_gross_sales")
    private BigDecimal grossSales;
    @Column(name = "ts_discount")
    private BigDecimal discount;
    @Column(name = "ts_net_sales")
    private BigDecimal netSales;
    @Column(name = "ts_sales_vat_exclusive")
    private BigDecimal salesVatExclusive;
    @Column(name = "ts_ewt")
    private BigDecimal ewt;
    @Column(name = "ts_service_charge")
    private BigDecimal serviceCharge;
    @Column(name = "ts_stripping_fee")
    private BigDecimal strippingFee;
    @Column(name = "ts_return")
    private BigDecimal tsReturn;
    @Column(name = "ts_interest_income")
    private BigDecimal interestIncome;
    @Column(name = "ts_balance2")
    private BigDecimal balance2;
    @Column(name = "ts_particulars")
    private String particulars;
    @Column(name = "ts_bank")
    private String bank;
    @Column(name = "ts_from_bank_sub")
    private String fromBankSub;
    @Column(name = "ts_to_bank_sub")
    private String toBankSub;
    @Column(name = "ts_amount")
    private BigDecimal amount;
}

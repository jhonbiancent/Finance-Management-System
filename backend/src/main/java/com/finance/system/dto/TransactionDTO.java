package com.finance.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private String subsidiaryName;
    private String disbursementName;
    private String logisticsInfo; // e.g., driver name - plate number
    private String productName;
    private String accountsReceivableName;
    private String controllerName;
    private String accountsPayableName;
    private String userName;
    private String checkNumber;
    private Date checkDate;
    private String status;
    private BigDecimal balance;
    private Date dueDate;
    private Integer termsOfDays;
    private String mode;
    private String type;
    private Integer quantity;
    private BigDecimal fuelPurchase;
    private String invoiceBillingNumber;
    private BigDecimal freightAmount;
    private String freightBilling;
    private String commissionName;
    private BigDecimal commissionAmount;
    private BigDecimal commissionOnBilling;
    private BigDecimal payments;
    private String remarks;
    private String backtrack;
    private String status2;
    private String status3;
    private String group;
    private String revenueCategory;
    private String billingNumber;
    private String description;
    private String bmmiCustomer;
    private String tin;
    private String vatNonvat;
    private BigDecimal vat;
    private String address;
    private BigDecimal grossSales;
    private BigDecimal discount;
    private BigDecimal netSales;
    private BigDecimal salesVatExclusive;
    private BigDecimal ewt;
    private BigDecimal serviceCharge;
    private BigDecimal strippingFee;
    private BigDecimal returnValue;
    private BigDecimal interestIncome;
    private String particulars;
    private String bank;
    private String fromBankSub;
    private String toBankSub;
    private BigDecimal amount;
}

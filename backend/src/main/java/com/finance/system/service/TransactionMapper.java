package com.finance.system.service;

import com.finance.system.dto.TransactionDTO;
import com.finance.system.model.Transaction;

public class TransactionMapper {

    public static TransactionDTO toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return TransactionDTO.builder()
                .id(transaction.getId())
                .subsidiaryName(transaction.getSubsidiary() != null ? transaction.getSubsidiary().getSubsidiaryName() : null)
                .disbursementName(transaction.getDisbursement() != null ? transaction.getDisbursement().getName() : null)
                .logisticsInfo(transaction.getLogistics() != null ? transaction.getLogistics().getDriverName() + " - " + transaction.getLogistics().getPlateNumber() : null)
                .productName(transaction.getProducts() != null ? transaction.getProducts().getType() : null)
                .accountsReceivableName(transaction.getAccountsReceivable() != null ? transaction.getAccountsReceivable().getName() : null)
                .controllerName(transaction.getController() != null ? transaction.getController().getName() : null)
                .accountsPayableName(transaction.getAccountsPayable() != null ? transaction.getAccountsPayable().getName() : null)
                .userName(transaction.getUser() != null ? transaction.getUser().getFirstname() + " " + transaction.getUser().getLastname() : null)
                .checkNumber(transaction.getCheckNumber())
                .checkDate(transaction.getCheckDate())
                .status(transaction.getStatus())
                .balance(transaction.getBalance())
                .dueDate(transaction.getDueDate())
                .termsOfDays(transaction.getTermsOfDays())
                .mode(transaction.getMode())
                .type(transaction.getType())
                .quantity(transaction.getQuantity())
                .fuelPurchase(transaction.getFuelPurchase())
                .invoiceBillingNumber(transaction.getInvoiceBillingNumber())
                .freightAmount(transaction.getFreightAmount())
                .freightBilling(transaction.getFreightBilling())
                .commissionName(transaction.getCommissionName())
                .commissionAmount(transaction.getCommissionAmount())
                .commissionOnBilling(transaction.getCommissionOnBilling())
                .payments(transaction.getPayments())
                .remarks(transaction.getRemarks())
                .backtrack(transaction.getBacktrack())
                .status2(transaction.getStatus2())
                .status3(transaction.getStatus3())
                .group(transaction.getGroup())
                .revenueCategory(transaction.getRevenueCategory())
                .billingNumber(transaction.getBillingNumber())
                .description(transaction.getDescription())
                .bmmiCustomer(transaction.getBmmiCustomer())
                .tin(transaction.getTin())
                .vatNonvat(transaction.getVatNonvat())
                .vat(transaction.getVat())
                .address(transaction.getAddress())
                .grossSales(transaction.getGrossSales())
                .discount(transaction.getDiscount())
                .netSales(transaction.getNetSales())
                .salesVatExclusive(transaction.getSalesVatExclusive())
                .ewt(transaction.getEwt())
                .serviceCharge(transaction.getServiceCharge())
                .strippingFee(transaction.getStrippingFee())
                .returnValue(transaction.getReturnValue())
                .interestIncome(transaction.getInterestIncome())
                .particulars(transaction.getParticulars())
                .bank(transaction.getBank())
                .fromBankSub(transaction.getFromBankSub())
                .toBankSub(transaction.getToBankSub())
                .amount(transaction.getAmount())
                .build();
    }
}

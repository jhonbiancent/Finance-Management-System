package com.finance.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subsidiary")
public class Subsidiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SB_subsidiary_id")
    private Long id;

    @Column(name = "SB_subsidiary")
    private String subsidiaryName;

    @Column(name = "SB_account_category")
    private String accountCategory;

    @Column(name = "SB_po_wb_number_form_number")
    private String poWbNumberFormNumber;

    @Column(name = "SB_client")
    private String client;

    @Column(name = "SB_supplier")
    private String supplier;

    @Column(name = "SB_billing_number")
    private String billingNumber;

    @Column(name = "SB_client_id")
    private String clientId;
}

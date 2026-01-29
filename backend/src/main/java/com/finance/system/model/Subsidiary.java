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
    private Long sb_subsidiary_id;

    private String sb_subsidiary;
    private String sb_account_category;
    private String sb_po_wb_number_form_number;
    private String sb_client;
    private String sb_supplier;
    private String sb_billing_number;
    private String sb_client_id;
}

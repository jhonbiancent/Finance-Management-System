package com.finance.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disbursement")
public class Disbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DS_disbursement_id")
    private Long id;

    private String name;

    @Column(name = "DS_disbursement_voucher")
    private String disbursementVoucher;

    @Column(name = "DS_year")
    private Integer year;

    @Column(name = "DS_month")
    private Integer month;

    @Column(name = "DS_date")
    private Date date;

    @Column(name = "DS_check_number")
    private String checkNumber;
}

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
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PR_PRODUCTS_ID")
    private Long id;

    @Column(name = "PR_type")
    private String type;

    @Column(name = "PR_unity_price")
    private BigDecimal unitPrice;

    @Column(name = "PR_unit")
    private String unit;
}

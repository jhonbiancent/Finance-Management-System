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
@Table(name = "controller")
public class Controller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CR_id")
    private Long id;

    private String name;

    @Column(name = "CR_date_approved")
    private Date dateApproved;
}

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
@Table(name = "logistics")
public class Logistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LG_logistic_id")
    private Long id;

    @Column(name = "LG_fleet_type")
    private String fleetType;

    @Column(name = "LG_driver_name")
    private String driverName;

    @Column(name = "LG_plate_num")
    private String plateNumber;
}

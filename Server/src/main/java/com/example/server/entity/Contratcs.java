package com.example.server.entity;

import com.example.server.entity.enums.ContractType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contratcs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @Column(name="contract_start_date", nullable = false)
    private LocalDate contractStartDate;

    @Column(name="contract_end_date", nullable = false)
    private LocalDate contractEndDate;
    private String contractPath;

    @Column(name="contract_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;
}


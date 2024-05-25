package com.example.server.entity;

import com.example.server.entity.enums.StatusEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Long employeeId;

    @Column(name="account_number_employee", nullable = false)
    private Long accountNumberEmployee;

    @Column(name="salary_base", nullable = false)
    private Float salaryBase;

    @Column(name="status_employee", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEmployee statusEmployee;


    @OneToOne(targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", nullable = false)
    private UserEntity person;

    @ManyToOne(targetEntity = CostCenter.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", nullable = false)
    private CostCenter costCenter;

    @ManyToOne(targetEntity = Position.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
}


package com.example.server.entity;

import com.example.server.entity.enums.StatusEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Affiliations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long affiliationId;

    @Column(name="entity_code", nullable = false)
    private Long entityCode;

    @Column(name="entity_type", nullable = false)
    private String entityType;

    @Column(name="entity_name", nullable = false)
    private String entityName;

    @Column(name="affiliation_date", nullable = false)
    private LocalDate affiliationDate;

    @Column(name="affiliation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEmployee affiliationStatus;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;

}


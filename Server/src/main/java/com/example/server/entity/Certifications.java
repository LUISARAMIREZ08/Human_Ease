package com.example.server.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "certifications")
public class Certifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;

    @Column(name="certification_name", nullable = false, length = 200)
    private String certificationName;

    @Column(name="certification_entity", nullable = false, length = 200)
    private String certificationEntity;

    @Column(name="certification_date_of_issue", nullable = false)
    private LocalDate certificationDateOfIssue;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employeeId;
}

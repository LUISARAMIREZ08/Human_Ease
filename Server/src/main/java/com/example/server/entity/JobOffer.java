package com.example.server.entity;

import com.example.server.entity.enums.JobOfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOfferId;

    @Column(name="job_offer_name", nullable = false, length = 100)
    private String jobOfferName;

    @Column(name="job_offer_description", nullable = false, length = 1000)
    private String jobOfferDescription;

    @Column(name="job_offer_date", nullable = false)
    private LocalDate jobOfferDate;

    @Column(name="job_offer_expiration_date", nullable = false)
    private LocalDate jobOfferExpirationDate;

    @Column(name="job_offer_Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobOfferStatus jobOfferStatus;

    @Column(name="salary", nullable = false)
    private Float salary;

    @ManyToOne(targetEntity = Position.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}


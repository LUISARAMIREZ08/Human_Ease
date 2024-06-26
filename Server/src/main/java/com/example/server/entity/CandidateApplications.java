package com.example.server.entity;

import com.example.server.entity.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate_applications")
public class CandidateApplications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateApplicationId;

    @Column(name="application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name="application_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(targetEntity = JobOffer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;
}


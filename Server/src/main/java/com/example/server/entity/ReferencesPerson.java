package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "referencesPerson")
public class ReferencesPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenceId;

    @Column(name="reference_name", nullable = false, length = 100)
    private String referenceName;

    @Column(name="reference_phone", nullable = false, length = 20)
    private String referencePhone;

    @Column(name="reference_email", nullable = false, length = 200)
    private String referenceResult;

    @ManyToOne(targetEntity = CandidateApplications.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_application_id", nullable = false)
    private CandidateApplications candidateApplicationsId;
}

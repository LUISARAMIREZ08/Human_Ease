package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "record_person")
public class RecordPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(name="record_type", nullable = false, length = 100)
    private String recordType;

    @Column(name="record_result", nullable = false, length = 100)
    private String recordResult;

    @Column(name="record_date_verified", nullable = false, length = 100)
    private String recordDateVerified;

    @ManyToOne(targetEntity = CandidateApplications.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_application_id", nullable = false)
    private CandidateApplications candidateApplications;
}

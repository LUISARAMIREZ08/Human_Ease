package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "psychotechnical_tests")
public class PsychotechnicalTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psychotechnicalTestId;

    @Column(name="test_type", nullable = false, length = 100)
    private String testType;

    @Column(name="test_date", nullable = false)
    private LocalDate testDate;

    @Column(name="test_time", nullable = false, length = 100)
    private String testTime;

    @Column(name="test_result", nullable = false, length = 100)
    private String testResult;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_application_id", nullable = false)
    private CandidateApplications candidateApplications;

}

package com.example.server.entity;

import com.example.server.entity.enums.InterviewStatus;
import com.example.server.entity.enums.InterviewType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interviews")
public class Interviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @Column(name="interview_date", nullable = false)
    private LocalDate interviewDate;

    @Column(name="interview_time", nullable = false, length = 100)
    private String interviewTime;

    @Column(name="interview_result", nullable = false, length = 100)
    private String result;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewStatus interviewStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_application_id", nullable = false)
    private CandidateApplications candidateApplicationsId;
}


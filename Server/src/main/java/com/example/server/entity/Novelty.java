package com.example.server.entity;

import com.example.server.entity.enums.NoveltyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "novelty")
public class Novelty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noveltyId;

    @Column(name="date_novelty", nullable = false)
    private LocalDate dateNovelty;

    @Column(name="cuttoff_date", nullable = false)
    private LocalDate cuttoffDate;

    @Column(name="value_concept", nullable = false)
    private Float valueConcept;
    private Long cuantityConcept;

    @Column(name="novelty_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NoveltyType noveltyType;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id")
    private Concept conceptId;

    @ManyToOne(targetEntity = Periods.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", nullable = false)
    private Periods periodId;

}


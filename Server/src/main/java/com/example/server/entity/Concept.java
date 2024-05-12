package com.example.server.entity;

import com.example.server.entity.enums.ConceptType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "concept")
public class Concept {
    @Id
    private Long conceptId;

    @Column(name="name_concept", nullable = false)
    private String nameConcept;

    @Column(name="concept_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConceptType conceptType;
}


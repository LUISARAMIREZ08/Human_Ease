package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requirements")
public class Requirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requirementId;

    @Column(name="name_requirement", nullable = false, length = 200)
    private String descriptionRequirement;

    @ManyToOne(targetEntity = Position.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position positionId;

}

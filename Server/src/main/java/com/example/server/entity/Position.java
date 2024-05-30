package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    @Column(name="name_position", nullable = false, length = 100)
    private String namePosition;

    @Column(name="level_of_experience", nullable = false, length = 100)
    private String levelOfExperience;

    @Column(name="employment_time", nullable = false, length = 100)
    private String employmentTime;

    @ManyToOne(targetEntity = Departments.class)
    @JoinColumn(name = "department_id", nullable = false)
    private Departments department;
}

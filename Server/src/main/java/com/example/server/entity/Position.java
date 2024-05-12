package com.example.server.entity;

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

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Departments department;

    @OneToMany(targetEntity = Requirements.class, mappedBy = "positionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requirements> requirementsId;
}

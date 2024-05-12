package com.example.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cost_center")
public class CostCenter {
    @Id
    private Long costCenterId;

    @Column(name="cost_center_name", nullable = false, length = 100)
    private String costCenterName;
}

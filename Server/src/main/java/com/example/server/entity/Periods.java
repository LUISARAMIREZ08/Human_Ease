package com.example.server.entity;

import com.example.server.entity.enums.PeriodNum;
import com.example.server.entity.enums.TypePayroll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "periods")
public class Periods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long periodId;

    @Column(name="year", nullable = false)
    private Long year;

    @Column(name="month", nullable = false)
    private Long month;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Column(name="paymen_date", nullable = false)
    private LocalDate paymenDate;

    @Column(name="period_num", nullable = false)
    private PeriodNum periodNum;

    @Column(name="type_payroll", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePayroll typePayroll;
}


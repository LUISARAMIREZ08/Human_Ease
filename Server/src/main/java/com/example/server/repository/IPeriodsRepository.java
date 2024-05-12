package com.example.server.repository;

import com.example.server.entity.Periods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeriodsRepository extends JpaRepository<Periods, Long> {
}

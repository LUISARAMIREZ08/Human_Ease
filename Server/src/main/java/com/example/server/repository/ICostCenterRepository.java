package com.example.server.repository;

import com.example.server.entity.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICostCenterRepository extends JpaRepository<CostCenter, Long> {
}

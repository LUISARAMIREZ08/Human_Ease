package com.example.server.repository;

import com.example.server.entity.PsychotechnicalTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPsychotechnicalTestsRepository extends JpaRepository<PsychotechnicalTests, Long> {
}

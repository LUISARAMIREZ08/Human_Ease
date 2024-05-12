package com.example.server.repository;

import com.example.server.entity.CandidateApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidateApplicationsRepository extends JpaRepository<CandidateApplications, Long> {
}

package com.example.server.repository;

import com.example.server.entity.CandidateApplications;
import com.example.server.entity.JobOffer;
import com.example.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICandidateApplicationsRepository extends JpaRepository<CandidateApplications, Long> {
    @Query("SELECT e FROM CandidateApplications e WHERE e.jobOffer = :jobOffer AND e.userEntity = :userEntity")
    List<CandidateApplications> findCandidateApplicationsByJobOffer(@Param("jobOffer") JobOffer jobOffer, @Param("userEntity") UserEntity userEntity);
}
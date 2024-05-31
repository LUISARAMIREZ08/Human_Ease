package com.example.server.repository;

import com.example.server.entity.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInterviewsRepository extends JpaRepository<Interviews, Long> {
    @Query("SELECT i FROM Interviews i WHERE i.candidateApplications.candidateApplicationId = :candidateApplicationId")
    Optional<Interviews> findByCandidateApplicationId(@Param("candidateApplicationId") Long candidateApplicationId);
}

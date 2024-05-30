package com.example.server.repository;

import com.example.server.controller.response.JobOfferJoin;
import com.example.server.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IJobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Query("SELECT new com.example.server.controller.response.JobOfferJoin(" +
            "u.name,u.lastName, u.cardId, e.applicationDate, e.applicationStatus) " +
            "FROM CandidateApplications e " +
            "JOIN e.jobOffer emp " +
            "JOIN e.userEntity u" +
            " WHERE emp.jobOfferId = :id")

    List<JobOfferJoin> findJobOfferDetails(@PathVariable Long id);
}

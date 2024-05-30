package com.example.server.repository;

import com.example.server.controller.response.JobOfferJoin;
import com.example.server.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobOfferRepository extends JpaRepository<JobOffer, Long> {

    @Query("SELECT new com.example.server.controller.response.JobOfferJoin(" +
            "e.jobOfferName, e.jobOfferDate, ue.name, ue.lastName, ue.cardId, e.jobOfferStatus) " +
            "FROM JobOffer e " +
            "JOIN e.employee emp " +
            "JOIN emp.userEntity ue")

    List<JobOfferJoin> findJobOfferDetails();
}

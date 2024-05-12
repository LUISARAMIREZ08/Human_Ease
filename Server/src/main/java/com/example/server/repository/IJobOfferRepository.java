package com.example.server.repository;

import com.example.server.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobOfferRepository extends JpaRepository<JobOffer, Long> {
}

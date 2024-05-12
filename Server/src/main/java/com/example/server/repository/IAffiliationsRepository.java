package com.example.server.repository;

import com.example.server.entity.Affiliations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAffiliationsRepository extends JpaRepository<Affiliations, Long> {
}

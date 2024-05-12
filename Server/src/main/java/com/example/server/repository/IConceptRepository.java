package com.example.server.repository;

import com.example.server.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConceptRepository extends JpaRepository<Concept, Long> {
}

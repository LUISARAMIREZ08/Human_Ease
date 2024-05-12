package com.example.server.repository;

import com.example.server.entity.ReferencesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReferencesPersonRepository extends JpaRepository<ReferencesPerson, Long> {
}
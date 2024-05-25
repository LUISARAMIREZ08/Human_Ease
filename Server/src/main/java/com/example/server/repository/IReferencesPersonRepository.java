package com.example.server.repository;

import com.example.server.entity.ReferencesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReferencesPersonRepository extends JpaRepository<ReferencesUser, Long> {
}
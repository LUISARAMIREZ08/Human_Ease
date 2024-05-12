package com.example.server.repository;

import com.example.server.entity.Contratcs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratcsRepository extends JpaRepository<Contratcs, Long> {
}

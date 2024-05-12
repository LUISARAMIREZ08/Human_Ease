package com.example.server.repository;

import com.example.server.entity.Novelty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoveltyRepository extends JpaRepository<Novelty, Long> {
}
